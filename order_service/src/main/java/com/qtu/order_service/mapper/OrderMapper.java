package com.qtu.order_service.mapper;

import com.qtu.entity.BmInOrderList;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Hu Shengkai
 * @create 2020-01-13 20:04
 */
@Mapper
public interface OrderMapper {
    int insert(BmInOrderList record);
    BmInOrderList selectByOrderId(String orderId);
    int updateByPrimaryKeySelective(BmInOrderList record);
}
