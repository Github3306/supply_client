package com.qtu.user_service.service;

import com.qtu.entity.BmMember;
import com.qtu.user_service.sjyBean.TaotaoResult;
import com.qtu.util.CacheResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface LoginService {

    public TaotaoResult MemberLogin(String tel, String password, HttpServletRequest request, HttpServletResponse response);

    public TaotaoResult getMemberByToken(String token);

   // public TaotaoResult logOut(String token);

    public BmMember selectByToken(String token);

    public int register(BmMember member);


}
