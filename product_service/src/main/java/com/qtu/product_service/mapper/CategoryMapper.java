package com.qtu.product_service.mapper;

import com.qtu.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    /**
     * 添加父类目
     * @param record
     * @return
     */
    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

}