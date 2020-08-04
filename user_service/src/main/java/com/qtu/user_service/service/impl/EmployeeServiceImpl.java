package com.qtu.user_service.service.impl;

import com.qtu.bean.EmployeeBean;
import com.qtu.entity.BmMember;
import com.qtu.entity.SysEmployee;
import com.qtu.user_service.mapper.EmployeeMapper;
import com.qtu.user_service.service.IEmployeeService;
import com.qtu.util.MyResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @ClassName: EmployeeServiceImpl
 * @author: lws
 * @create: 2020-01-14 10:19
 * @description: 添加，删除，更新，需要在方法上加事务提交 @Transactional
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public MyResult selectEmpInfo(String token) {

        return MyResult.ok(employeeMapper.selectEmployeeByToken(token));
    }

    @Transactional
    @Override
    public MyResult updateEmployeeImageUrl(String imageUrl, String employeeCode) {

        return MyResult.ok(employeeMapper.updateImageUrl(imageUrl,employeeCode));
    }

    @Override
    public MyResult changePassword(String oldPassword, String newPassword, Integer id) {
        SysEmployee sysEmployee = employeeMapper.selectByPrimaryKey(id);
        MyResult myResult = null;
        int rows = 0;
        if (oldPassword.equals(sysEmployee.getPassword())){
            if (!newPassword.equals(sysEmployee.getPassword())){
                rows = employeeMapper.changePassword(newPassword, id);
                myResult = new MyResult(200,"密码修改成功！",rows);
            }else {
                myResult= new MyResult(500,"新登陆密码和旧密码不能相同！",-1);
            }
        }else {
            myResult= new MyResult(500,"旧密码输入不正确！请重新输入",-1);
        }
        return myResult;
    }
}
