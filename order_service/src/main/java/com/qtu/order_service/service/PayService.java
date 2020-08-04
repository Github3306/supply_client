package com.qtu.order_service.service;

import com.qtu.util.MyResult;

/**
 * 支付业务
 * @author Hu Shengkai
 * @create 2020-01-14 15:29
 */
public interface PayService {
    /**
     * 支付
     * @param orderId
     * @param payType
     * @return
     */
    public MyResult goPay(String orderId, Integer payType);
}
