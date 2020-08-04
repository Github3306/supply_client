package com.qtu.user_service.mapper;

import com.qtu.entity.BmMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SjyMemberMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(BmMember record);

    int insertSelective(BmMember record);

    BmMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BmMember record);

    int updateByPrimaryKey(BmMember record);

    //通过手机号查询到店铺用户
    BmMember selectByTel(@Param("tel") String tel);


    //不从redis中调取，普通的根据token获得当前登录用户
    BmMember selectByToken(@Param("token")String token);




}
