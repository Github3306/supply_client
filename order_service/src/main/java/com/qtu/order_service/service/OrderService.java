package com.qtu.order_service.service;

import com.qtu.entity.BmGoods;
import com.qtu.order_service.entity.CheckOutBean;
import com.qtu.util.MyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hu Shengkai
 * @create 2020-01-07 10:45
 */
public interface OrderService {
    /**
     * 创建订单
     * @return
     */
    public MyResult createOrder(String token, HttpServletRequest request , String addressId , String mark , CheckOutBean checkOutBean);
}
