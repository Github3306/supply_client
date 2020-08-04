package com.qtu.product_service.mapper;

import com.qtu.entity.SysPosition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysPositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPosition record);

    int insertSelective(SysPosition record);

    SysPosition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPosition record);

    int updateByPrimaryKey(SysPosition record);
    /*查询所有岗位信息*/
    List<SysPosition> selectAllPositionInfo();
}