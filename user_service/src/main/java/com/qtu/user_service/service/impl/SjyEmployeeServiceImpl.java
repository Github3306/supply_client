package com.qtu.user_service.service.impl;

import com.qtu.entity.SysEmployee;
import com.qtu.user_service.client.RedisFeignClient;
import com.qtu.user_service.mapper.SjyEmployeeMapper;

import com.qtu.user_service.service.SjyEmployeeService;
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
public class SjyEmployeeServiceImpl implements SjyEmployeeService {

    @Resource
    private SjyEmployeeMapper sjyEmployeeMapper;

    @Resource
    private RedisFeignClient redisFeignClient;


    @Override
    public TaotaoResult selectByTel(String tel, String password, HttpServletRequest request, HttpServletResponse response) {
        SysEmployee sysEmployee = sjyEmployeeMapper.selectByTel(tel);
        if(sysEmployee==null||sysEmployee.equals("")){
            return TaotaoResult.build(500,"用户名或密码错误");
        }
        if (!sysEmployee.getPassword().equals(password)){

            return TaotaoResult.build(500,"用户名或密码错误");
        }
        //清空对象密码
        sysEmployee.setPassword(null);
        String token = sysEmployee.getToken();
        //将用户信息放入redis中，时间为一天
        redisFeignClient.setToRedis(token,sysEmployee,new Long(60*60*24));
        //添加写cookie的逻辑，cookie的有效期是关闭浏览器就失效
        CookieUtils.setCookie(request,response,"TOKEN_EMPLOYEE",token);
        //返回token
        return TaotaoResult.ok(token);
    }

    @Override
    public TaotaoResult getEmployeeByToken(String token) {
        //根据token从redis中查询出员工信息
        CacheResult result = redisFeignClient.getFromRedis(token);
        SysEmployee sysEmployee = (SysEmployee) CacheResult.linkedHashMapToObject(result.getData(),SysEmployee.class);
        //判断是否为空
        if(sysEmployee==null||sysEmployee.equals("")){
            return TaotaoResult.build(400,"此session已经过期了");
        }
        return TaotaoResult.ok(sysEmployee);
    }

    @Override
    public TaotaoResult register(SysEmployee sysEmployee) {

        String []s = sjyEmployeeMapper.selectTel();
        int num = 0;
        for(int i=0;i<s.length;i++){
            if (s[i].equals(sysEmployee.getTel())){
                num=1;
            }
        }
        if(num==0){
            int insert = sjyEmployeeMapper.insert(sysEmployee);
            System.out.println("insert:"+insert);
            if (insert>0){
                return TaotaoResult.ok();
            }
            return TaotaoResult.build(400,"注册失败");
        }
        return TaotaoResult.build(400,"手机号已经被注册");
    }

    /**
     *  @Explain：修改员工密码
     *  @Author Shi jy
     *  @Date 2020/1/13 20:54
     *  @Param
    */
    @Override
    public TaotaoResult updatePassword(String tel, String password) {

        int i = sjyEmployeeMapper.upadatePassword(tel, password);
        if(i>0){
            return TaotaoResult.build(200,"修改成功");
        }
        return TaotaoResult.build(400,"修改失败");


    }

    @Override
    public TaotaoResult selectTel(String tel) {
        String []s = sjyEmployeeMapper.selectTel();
        int row = 0;;
        for(int i=0;i<s.length;i++){
            if(s[i].equals(tel)){
                row=1;
            }
        }
        if (row==1){
            return TaotaoResult.build(200,"手机号存在");
        }
        return TaotaoResult.build(400,"手机号不存在");
    }
}
