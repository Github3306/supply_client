package com.qtu.user_service.mapper;

import com.qtu.bean.EmployeeBean;
import com.qtu.entity.SysEmployee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysEmployee record);

    int insertSelective(SysEmployee record);

    SysEmployee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysEmployee record);

    int updateByPrimaryKey(SysEmployee record);

    /**
     * @Description: 上传更新员工头像
     * @Param: [imgUrl, id]
     * @return: int
     * @Author: lws
     * @Date: 2020/1/14 10:44
     */
    int updateImageUrl(@Param("imageUrl") String imgUrl, @Param("employeeCode") String employeeCode);

    /**
     * @Description: 通过当前登陆员工的token查询员工信息,关联性别和岗位表
     * @Param: [token]
     * @return: com.qtu.entity.SysEmployee
     * @Author: lws
     * @Date: 2020/1/14 11:04
     */
    EmployeeBean selectEmployeeByToken(String token);

    /**
     * @Description: 更改登陆密码
     * @Param: [newPassword, token]
     * @return: int
     * @Author: lws
     * @Date: 2020/1/14 10:41
     */
    int changePassword(@Param("newPassword") String newPassword,@Param("id") Integer id);


    /**
     * @Description: 更新员工信息
     * @Param: [employeeCode]
     * @return: int
     */
    int updateEmployee(SysEmployee record);



}