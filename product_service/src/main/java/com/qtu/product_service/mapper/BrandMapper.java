package com.qtu.product_service.mapper;

import com.qtu.entity.Brand;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    Brand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

}