package com.qtu.user_service.controller;

import com.qtu.user_service.service.IEmployeeService;
import com.qtu.util.MyResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @ClassName: EmployeeController
 * @author: lws
 * @create: 2020-01-14 10:51
 * @description:
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private IEmployeeService employeeService;

    /**
     * @Description: 关联性别表，岗位表 查询员工信息
     * @Param: [token]
     * @return: java.lang.Object
     * @Author: lws
     * @Date: 2020/1/14 15:42
     */
    @GetMapping("/getInfo")
    public Object selectEmpInfo(String token){

        return employeeService.selectEmpInfo(token).getData();
    }

    /**
     * @Description: 通过传回的id修改登陆密码
     * @Param: [oldPassword, newPassword, id]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/14 15:42
     */
    @PostMapping("/changePassword")
    public MyResult changePassword(String oldPassword, String newPassword, Integer id){

        return employeeService.changePassword(oldPassword,newPassword,id);
    }

}
