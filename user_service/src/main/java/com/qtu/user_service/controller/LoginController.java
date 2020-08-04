package com.qtu.user_service.controller;

import com.qtu.entity.BmMember;
import com.qtu.user_service.service.LoginService;
import com.qtu.user_service.sjyBean.TaotaoResult;
import com.qtu.util.CacheResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService;


    @PostMapping("/login")
    @ResponseBody
    public TaotaoResult MemberLogin(String tel,String password,HttpServletRequest request, HttpServletResponse response){

        System.out.println("电话："+tel);
        TaotaoResult res =new TaotaoResult();
        try{
             res = loginService.MemberLogin(tel, password, request, response);
            return res;
        }catch (Exception e){
            e.printStackTrace();

            return TaotaoResult.build(500,"错误");
        }

    }


    @GetMapping(value="/selectByToken")
    @ResponseBody
    public TaotaoResult getMemberByToken(String token){
        TaotaoResult res =new TaotaoResult();
        TaotaoResult memberByToken = loginService.getMemberByToken(token);

        return memberByToken;
    }

    //退出
   /* @GetMapping(value="/logOut")
    @ResponseBody
    public Object logOut(String token){
        TaotaoResult res = new TaotaoResult();
       try{
            res = loginService.logOut(token);
       }catch (Exception e){
           e.printStackTrace();
           res = TaotaoResult.build(500, "报错了");
       }
        return null;
    }*/



    @PostMapping(value="/register")
    @ResponseBody
    public TaotaoResult register(String tel,String trueName,
    String businessName,String detailedAddress){
        System.out.println("tel"+tel);
        System.out.println("businessName"+businessName);
        System.out.println("trueName"+trueName);
        System.out.println("detailedAddress"+detailedAddress);
        BmMember bmMember = new BmMember();
        bmMember.setTel(tel);
        bmMember.setTrueName(trueName);
        bmMember.setBusinessName(businessName);
        bmMember.setAddress(detailedAddress);
        String token = UUID.randomUUID().toString();
        bmMember.setToken(token);
        bmMember.setState(1);
        bmMember.setBusiness(0);
        bmMember.setIsDeleted(1);
        bmMember.setAddTime(new Date());

        int register = loginService.register(bmMember);
        if(register>0){
            return TaotaoResult.ok(register);
        }

        return TaotaoResult.build(400,"注册失败");

    }


    //普通查询token得到对象
    @ResponseBody
    @RequestMapping(value="selectMemberByToken")
    public BmMember selectMemberByToken(String token){
        BmMember result = loginService.selectByToken(token);
        return result;
    }

}
