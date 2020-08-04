package com.qtu.sendorders_service.service;

import com.qtu.entity.BmInOrderList;

/**
 * @author Hu Shengkai
 * @create 2020-01-14 15:24
 */
public interface SendOrderService {
    /**
     * 派单,为订单分配仓库管理人员和物流人员
     * @param orderId
     */
    public void sendOrder(String orderId);
}
