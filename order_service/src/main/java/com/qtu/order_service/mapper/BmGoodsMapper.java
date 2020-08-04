package com.qtu.order_service.mapper;

import com.qtu.entity.BmGoods;
import com.qtu.order_service.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BmGoodsMapper {
    CartItem selectCartItemBySpecificationId(Integer id);
}