package com.qtu.order_service.mapper;

import com.qtu.entity.Specification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface SpecificationMapper {
    int updateByPrimaryKeySelective(Specification record);
}