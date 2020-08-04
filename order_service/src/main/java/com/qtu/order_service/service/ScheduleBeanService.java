package com.qtu.order_service.service;

import com.qtu.order_service.bean.OrderBean;
import com.qtu.order_service.bean.ScheduleBean;

import java.util.List;
import java.util.Map;

/**
 * @author swl
 * @create 2020-01-10 14:37
 */
public interface ScheduleBeanService {
    /**
     * 获取所有订单列表
     */
    public List<ScheduleBean> getOrderList(Integer warehouserId,Integer orderStep);

    public List<ScheduleBean> getGoodsList(String orderId);

    public Map<String,Object> update(String checkId,String orderId,Integer orderStep);

    public Map<String,Object> getOneOrderInfo(String orderId,Integer orderStep);
}
