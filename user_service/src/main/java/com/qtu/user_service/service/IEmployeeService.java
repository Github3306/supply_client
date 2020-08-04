package com.qtu.user_service.service;

import com.qtu.util.MyResult;


/**
 * Created by IntelliJ IDEA.
 *
 * @ClassName: IEmployeeService
 * @author: lws
 * @create: 2020-01-14 10:18
 * @description:
 */
public interface IEmployeeService {

    /**
     * @Description: 通过token查找员工信息
     * @Param: [token]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/14 11:06
     */
    public MyResult selectEmpInfo(String token);

    /**
     * @Description: 上传及更新员工头像
     * @Param: [imageUrl, id]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/14 10:47
     */
    public MyResult updateEmployeeImageUrl(String imageUrl, String employeeCode);

    /**
     * @Description: 修改登陆密码
     * @Param: [oldPassword, newPassword, token]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/13 11:33
     */
    public MyResult changePassword(String oldPassword,String newPassword,Integer id);
}
