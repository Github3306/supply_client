package com.qtu.order_service.service.impl;

import com.qtu.entity.BmInOrderList;
import com.qtu.order_service.config.MyAMQPConfig;
import com.qtu.order_service.mapper.OrderMapper;
import com.qtu.order_service.service.PayService;
import com.qtu.util.MyResult;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Hu Shengkai
 * @create 2020-01-14 15:34
 */
@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public MyResult goPay(String orderId , Integer payType) {
        /*
            1.支付(未实现)
            2.支付完成,修改订单的状态
            3.发送消息队列,让派单服务进行派单
         */
        BmInOrderList order = new BmInOrderList();
        order.setOrderId(orderId);
        order.setOrderStep(2);
        order.setPayType(payType);
        order.setPayTime(new Date());
        //更新订单数据
        orderMapper.updateByPrimaryKeySelective(order);

        //发送消息
        rabbitTemplate.convertAndSend(MyAMQPConfig.EXCHANGE, MyAMQPConfig.KEY, orderId);
        return MyResult.ok("支付完成");
    }
}
