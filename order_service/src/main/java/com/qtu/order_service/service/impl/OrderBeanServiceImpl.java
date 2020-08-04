package com.qtu.order_service.service.impl;

import com.qtu.order_service.bean.OrderBean;
import com.qtu.order_service.mapper.OrderBeanMapper;
import com.qtu.order_service.service.OrderBeanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author swl
 * @create 2020-01-10 14:42
 */
@Service
public class OrderBeanServiceImpl implements OrderBeanService {

    @Resource
    private OrderBeanMapper orderBeanMapper;
    @Override
    public List<OrderBean> getOrderList(String sellerId, Integer orderStep) {
        List<OrderBean> orderBeanList = orderBeanMapper.selectBySellerId(sellerId, orderStep);
        return orderBeanList;
    }

    @Override
    public Map<String, Object> getOrderDet(String orderId) {
        Map<String, Object> map=new HashMap<>();
        OrderBean orderBean = orderBeanMapper.selectByOrderId(orderId);
        List<OrderBean> orderBeanList = orderBeanMapper.selectGoodsByOrderId(orderId);
        map.put("order",orderBean);
        map.put("list",orderBeanList);
        return map;
    }

    @Override
    public Map<String, Object> updateOrder(String orderId) {
        Map<String, Object> map=new HashMap<>();
        Integer updateOrder = orderBeanMapper.updateOrder(orderId);
        if(updateOrder>0){
            map.put("code",200);
        }
        return map;
    }
}
