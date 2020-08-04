package com.qtu.user_service.service.impl;


import com.qtu.entity.BmMember;
import com.qtu.redis_service.util.RedisUtil;
import com.qtu.user_service.client.RedisFeignClient;
import com.qtu.user_service.mapper.SjyMemberMapper;
import com.qtu.user_service.service.LoginService;
import com.qtu.user_service.sjyBean.TaotaoResult;
import com.qtu.util.CacheResult;
import com.qtu.util.CookieUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Resource
    private SjyMemberMapper sjyMemberMapper;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private RedisFeignClient redisFeignClient;




    @Override
    public TaotaoResult MemberLogin(String tel, String password, HttpServletRequest request, HttpServletResponse response) {

        BmMember bmMember = sjyMemberMapper.selectByTel(tel);
        TaotaoResult res = new TaotaoResult();

        if(bmMember==null){

            return res.build(400,"用户名或密码错误");
        }
        //比对密码
        if(!password.equals(bmMember.getPassword())){
            return res.build(400,"用户名或密码错误");
        }
        //清空对象密码
        bmMember.setPassword(null);
        String token = bmMember.getToken();
        /* 把用户信息放入redis中 ,缓存时间为一天*/
       redisFeignClient.setToRedis("MEMBER_TOKEN:"+token,bmMember,new Long(60*60*24));
        //添加写cookie的逻辑，cookie的有效期是关闭浏览器就失效
        CookieUtils.setCookie(request,response,"TT_TOKEN",token);
        //返回token
        return TaotaoResult.ok(token);
    }

    /*
    根据token来获得店铺用户
     */
    @Override
    public TaotaoResult getMemberByToken(String token) {
        //根据token从redis中查询店铺用户信息
        CacheResult r = redisFeignClient.getFromRedis(token);

        BmMember bmMember = (BmMember) CacheResult.linkedHashMapToObject(r.getData(),BmMember.class);
        //判断是否为空
        /*if(StringUtils.isEmpty(json)){
            return TaotaoResult.build(400, "此session已经过期了");
        }*/
        if(bmMember==null||bmMember.equals("")){

            return TaotaoResult.build(400,"此session已经过期了");
        }
        return TaotaoResult.ok(bmMember);
    }


    //退出
    /*@Override
    public TaotaoResult logOut(String token) {
        redisUtil.del("http://localhost:8888"+":"+token);
        return null;
    }*/

    @Override
    public BmMember selectByToken(String token) {
        BmMember bmMember = sjyMemberMapper.selectByToken(token);
        return bmMember;
    }

    @Override
    public int register(BmMember member) {
        int insert = sjyMemberMapper.insert(member);
        return insert;
    }
}
