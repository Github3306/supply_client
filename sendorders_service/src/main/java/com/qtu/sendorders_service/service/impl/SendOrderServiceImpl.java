package com.qtu.sendorders_service.service.impl;

import com.qtu.entity.BmInOrderList;
import com.qtu.entity.SysEmployee;
import com.qtu.sendorders_service.client.RedisFeignClient;
import com.qtu.sendorders_service.config.RabbitMQConfig;
import com.qtu.sendorders_service.mapper.OrderMapper;
import com.qtu.sendorders_service.service.SendOrderService;
import com.qtu.util.CacheResult;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

/**
 * @author Hu Shengkai
 * @create 2020-01-14 15:26
 */
@Service
public class SendOrderServiceImpl implements SendOrderService {
    @Autowired
    private RedisFeignClient redisFeignClient;
    @Autowired
    private OrderMapper orderMapper;
    //仓库工作人员缓存key
    private static final String WARE_HOUSE_EMPLOYEE = "WARE_HOUSE_EMPLOYEE";
    //物流人员缓存key
    private static final String LOGISTICS_EMPLOYEE = "LOGISTICS_EMPLOYEE";

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    @Override
    public void sendOrder(String orderId) {

        try {
        /*
            1.根据订单查询相应仓库的id
            2.查询redis 找到该仓库下的该分配任务仓库人员和物流人员
            3.如果redis没有,就从数据库查出,缓存到数据库中,注意使用list模拟队列的缓存
            4.出队第一个元素,把它的id赋给订单,然后重新入队
         */
            System.out.println("接收到消息,新订单:"+orderId);
            //1.根据订单查询相应仓库的id
            String warehouseCode = orderMapper.selectWarehouseCodeByOrderId(orderId);
            String whKey = WARE_HOUSE_EMPLOYEE+":"+warehouseCode;
            String lgKey = LOGISTICS_EMPLOYEE+":"+warehouseCode;

            //2.查询redis 找到该仓库下所有的仓库人员和物流人员
            //仓库人员
            CacheResult result = redisFeignClient.rightPopList(whKey);
            Object data = result.getData();
            Integer whEmpId = null;
            Integer lgEmpId = null;
            if (data == null){
                //等于null, 就从数据库查出,缓存到数据库中,注意使用list模拟队列的缓存
                // '3' 代表仓库配货工作人员
                List<Integer> whEmpList = orderMapper.selectEmployeeByWarehouseCodeAndPositionId(warehouseCode, 3);
                redisFeignClient.leftPushAllList(whKey, Collections.singletonList(whEmpList), -1L);//永久缓存
                whEmpId = whEmpList.get(whEmpList.size()-1);
            }else {
                //4.拿到之前出队第一个元素,把它的id赋给订单,然后重新入队
                whEmpId = (Integer) result.getData(); //列表中的最后一个员工
                redisFeignClient.leftPushList(whKey, whEmpId, -1L);
            }

            //物流人员
            result = redisFeignClient.rightPopList(lgKey);
            data = result.getData();
            if (data == null){
                //等于null, 就从数据库查出,缓存到数据库中,注意使用list模拟队列的缓存
                // '4' 代表仓库物流工作人员
                List<Integer> whEmpList = orderMapper.selectEmployeeByWarehouseCodeAndPositionId(warehouseCode, 4);
                lgEmpId = whEmpList.get(whEmpList.size()-1); //列表中的最后一个员工
                redisFeignClient.leftPushAllList(lgKey, Collections.singletonList(whEmpList), -1L);//永久缓存
            }else {
                //4.得到之前出队第一个元素,把它的id赋给订单,然后重新入队
                lgEmpId = (Integer) result.getData();
                redisFeignClient.leftPushList(lgKey, lgEmpId, -1L);
            }

            //更新订单信息
            BmInOrderList order = new BmInOrderList();
            order.setOrderId(orderId);
            order.setWarehouserId(whEmpId);
            order.setLogisticsId(lgEmpId);
            orderMapper.updateByPrimaryKeySelective(order);

            System.out.println("派单成功,订单号为:"+orderId);
        } catch (Exception e) {
            System.out.println("派单出现异常,订单号为:"+orderId);
            e.printStackTrace();
        }
    }
}
