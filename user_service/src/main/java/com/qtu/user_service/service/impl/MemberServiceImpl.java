package com.qtu.user_service.service.impl;

import com.qtu.entity.BmAddress;
import com.qtu.entity.BmMember;
import com.qtu.user_service.mapper.BmAddressMapper;
import com.qtu.user_service.mapper.MemberMapper;
import com.qtu.user_service.service.IMemberService;
import com.qtu.util.MyResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @ClassName: MemberServiceImpl
 * @author: lws
 * @create: 2020-01-09 17:23
 * @description: 添加，删除，更新，需要在方法上加事务提交 @Transactional
 */
@Service

public class MemberServiceImpl implements IMemberService {

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private BmAddressMapper addressMapper;

    @Override
    public MyResult selectByToken(String token) {

        return MyResult.ok(memberMapper.selectByToken(token));
    }

    @Override
    public MyResult listAddrByToken(String token) {

        return MyResult.ok(memberMapper.listAddrByToke(token));
    }

    @Override
    public MyResult insertAddress(BmAddress address) {

        return MyResult.ok(addressMapper.insert(address));
    }

    @Override
    public MyResult deleteAddress(Integer addId) {

        return MyResult.ok(addressMapper.deleteByPrimaryKey(addId));
    }

    @Override
    public MyResult changePassword(String oldPassword, String newPassword, String token) {
        BmMember bmMember = memberMapper.selectByToken(token);
        MyResult myResult = null;
        int rows = 0;
        if (oldPassword.equals(bmMember.getPassword())){
            if (!newPassword.equals(bmMember.getPassword())){
                rows = memberMapper.changePassword(newPassword, token);
                myResult = new MyResult(200,"密码修改成功！",rows);
            }else {
                myResult= new MyResult(500,"新登陆密码和旧密码不能相同！",-1);
            }
        }else {
            myResult= new MyResult(500,"旧密码输入不正确！请重新输入",-1);
        }
        return myResult;
    }

    @Override
    public MyResult changePayPassword(String oldPayPassword, String newPayPassword, String token) {
        BmMember bmMember = memberMapper.selectByToken(token);
        MyResult myResult = null;
        int rows = 0;
        if (oldPayPassword.equals(bmMember.getPayPassword())){
            if (!newPayPassword.equals(bmMember.getPayPassword())){
                rows = memberMapper.changePayPassword(newPayPassword, token);
                myResult = new MyResult(200,"支付密码修改成功！",rows);
            }else {
                myResult = new MyResult(500,"新支付密码和旧密码不能相同！",-1);
            }
        }else {
            myResult = new MyResult(500,"旧密码输入不正确！请重新输入",-1);
        }

        return myResult;
    }

    @Transactional
    @Override
    public MyResult changeDefault(String token,Integer addId) {

        BmMember bmMember = memberMapper.selectByToken(token);
        List<BmAddress> list = addressMapper.listBySellerId(bmMember.getId());
        for (BmAddress addr:list) {
            addr.setIsDefault(0);
            addressMapper.updateByPrimaryKeySelective(addr);
        }
        for (BmAddress addr:list){
            if (addr.getAddId().equals(addId)){
                addr.setIsDefault(1);
                addressMapper.updateByPrimaryKeySelective(addr);
            }
        }

        return MyResult.ok();
    }

    @Override
    public MyResult getSellerTarAddress(Integer addId) {

        return MyResult.ok(addressMapper.selectByPrimaryKey(addId));
    }

    @Transactional
    @Override
    public MyResult editAddress(BmAddress address) {

        return MyResult.ok(addressMapper.updateByPrimaryKeySelective(address));
    }

    @Transactional
    @Override
    public MyResult updateSellerBackImg(String backImg, Integer id) {

        return MyResult.ok(memberMapper.updateBackImg(backImg,id));
    }

    @Override
    public MyResult findDefaultAddress(String token) {

        return MyResult.ok(addressMapper.selectByToken(token));
    }
}
