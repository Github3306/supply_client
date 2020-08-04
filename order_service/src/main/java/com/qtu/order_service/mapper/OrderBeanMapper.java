package com.qtu.order_service.mapper;

import com.qtu.order_service.bean.OrderBean;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @author swl
 * @create 2020-01-10 11:39
 */
@Mapper
public interface OrderBeanMapper {

    public List<OrderBean> selectBySellerId(@Param("sellerId") String sellerId, @Param("orderStep") Integer orderStep);

    public OrderBean selectByOrderId(String orderId);

    public List<OrderBean> selectGoodsByOrderId(String orderId);

    public Integer updateOrder(String orderId);
}
