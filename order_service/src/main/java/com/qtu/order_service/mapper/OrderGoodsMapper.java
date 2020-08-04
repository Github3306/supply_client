package com.qtu.order_service.mapper;

import com.qtu.entity.BmInOrderGoodsList;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Hu Shengkai
 * @create 2020-01-13 20:15
 */
@Mapper
public interface OrderGoodsMapper {
    int insert(BmInOrderGoodsList record);
}
