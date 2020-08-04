package com.qtu.user_service.controller;

import com.qtu.entity.SysEmployee;
import com.qtu.user_service.service.SjyEmployeeService;
import com.qtu.user_service.sjyBean.TaotaoResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
 *  @auther: Shi jy
 *  @Date: 2020/1/13 11:21
 */
@Controller
@RequestMapping("/employee")
public class SjyEmployeeController {

    @Resource
    private SjyEmployeeService employeeService;


    /**
     *  @Explain:员工登录
     *  @Author Shi jy
     *  @Date 2020/1/13 11:21
     *  @Param
    */
    @PostMapping(value = "/login")
    @ResponseBody
    public TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response){
        TaotaoResult result = employeeService.selectByTel(username,password,request,response);

        return result;
    }

    /**
     *  @Explain ：根据token从redis中查询出员工信息
     *  @Author Shi jy
     *  @Date 2020/1/13 17:18
     *  @Param
    */
    @GetMapping(value="/getToken")
    @ResponseBody
    public TaotaoResult getEmployeeByToken(String token){
        TaotaoResult result = employeeService.getEmployeeByToken(token);

        return result;
    }

    /**
     *  @Explain：员工注册
     *  @Author Shi jy
     *  @Date 2020/1/13 17:30
     *  @Param
    */
    @PostMapping(value="/register")
    @ResponseBody
    public TaotaoResult register(String tel,String ename,String password){
        SysEmployee employee = new SysEmployee();
        employee.setPassword(password);
        employee.setEname(ename);
        employee.setPositionId("1");
        employee.setTel(tel);
        employee.setCreateTime(new Date());
        String token = UUID.randomUUID().toString();
        employee.setToken(token);
        TaotaoResult register = employeeService.register(employee);
        return register;
    }

    /**
     *  @Explain:修改员工密码
     *  @Author Shi jy
     *  @Date 2020/1/13 20:57
     *  @Param
    */
    @PostMapping(value = "/updatePassword")
    @ResponseBody
    public TaotaoResult updatePassword(String phone,String password){
        System.out.println("修改员工密码");
        TaotaoResult result = employeeService.updatePassword(phone,password);
        return result;
    }

    /**
     *  @Explain:查询是否有这个手机号
     *  @Author Shi jy
     *  @Date 2020/1/14 11:13
     *  @Param
    */
    @PostMapping(value = "/selectTel")
    @ResponseBody
    public TaotaoResult selectTel(String phoneNumber){
        TaotaoResult result = employeeService.selectTel(phoneNumber);
        return result;
    }

}
