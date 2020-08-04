package com.qtu.user_service.service;

import com.qtu.entity.SysEmployee;
import com.qtu.user_service.sjyBean.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SjyEmployeeService {

    public TaotaoResult selectByTel(String tel, String password, HttpServletRequest request, HttpServletResponse response);

    public TaotaoResult getEmployeeByToken(String token);

    public TaotaoResult register(SysEmployee sysEmployee);

    public TaotaoResult updatePassword(String tel,String password);

    public TaotaoResult selectTel(String tel);
}
