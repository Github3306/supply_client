package com.qtu.order_service.service;

import com.qtu.order_service.bean.OrderBean;

import java.util.List;
import java.util.Map;

/**
 * @author swl
 * @create 2020-01-10 14:37
 */
public interface OrderBeanService {
    /**
     * 获取所有订单列表
     * @param sellerId
     * @param orderStep
     * @return
     */
    public List<OrderBean> getOrderList(String sellerId,Integer orderStep);

    public Map<String,Object> getOrderDet(String orderId);

    public Map<String,Object> updateOrder(String orderId);
}
