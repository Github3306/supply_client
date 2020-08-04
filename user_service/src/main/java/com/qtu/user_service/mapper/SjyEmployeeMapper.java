package com.qtu.user_service.mapper;

import com.qtu.entity.SysEmployee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
public interface SjyEmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysEmployee record);

    int insertSelective(SysEmployee record);

    SysEmployee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysEmployee record);

    int updateByPrimaryKey(SysEmployee record);


    /**
     *  @Explain:通过tel得到employee
     *  @Author Shi jy
     *  @Date 2020/1/13 10:41
     *  @Param
    */
    SysEmployee selectByTel(String tel);

    /**
     *  @Explain：查询所有的手机号
     *  @Author Shi jy
     *  @Date 2020/1/13 20:41
     *  @Param
    */
    String [] selectTel();

    int upadatePassword(@Param("tel") String tel,@Param("password") String password);



}