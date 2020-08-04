package com.qtu.order_service.controller;

import com.qtu.order_service.bean.OrderBean;
import com.qtu.order_service.bean.ScheduleBean;
import com.qtu.order_service.client.RedisFeignClient;
import com.qtu.order_service.service.ScheduleBeanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author swl
 * @create 2020-01-07 11:09
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Resource
    ScheduleBeanService scheduleBeanService;
    @Resource
    RedisFeignClient redisFeignClient;

    @GetMapping("test")
    @ResponseBody
    public Object test(){
        System.out.println("进入到此方法");
        Map<String,Object> map=new HashMap<>();
        map.put("code",200);
        return map;
    }
    @GetMapping("allList")
    @ResponseBody
    public Object allList(String token,Integer orderStep){
//        CacheResult fromRedis = redisFeignClient.getFromRedis(token);
//        Object data = fromRedis.getData();
//        BmMember user = (BmMember) CacheResult.linkedHashMapToObject(data, BmMember.class);
//        Integer id = user.getId();
        System.out.println("进入到allList()方法");
        Integer integer = Integer.valueOf(token);
        List<ScheduleBean> orderList = scheduleBeanService.getOrderList(integer, orderStep);
        return orderList;
    }
    @GetMapping("allGoods")
    @ResponseBody
    public Object allGoods(String orderId){
        List<ScheduleBean> goodsList = scheduleBeanService.getGoodsList(orderId);
        return goodsList;
    }
    @PostMapping("update")
    @ResponseBody
    public Object update(String checkId,String orderId,String orderStep){
        System.out.println(checkId+"--"+orderId+"--"+orderStep);
        Integer value = Integer.valueOf(orderStep);
        Map<String, Object> map = scheduleBeanService.update(checkId, orderId, value);
        return map;
    }
    @GetMapping("oneOrder")
    @ResponseBody
    public Object oneOrder(String orderId,String orderStep){
        Integer value = Integer.valueOf(orderStep);
        Map<String, Object> map = scheduleBeanService.getOneOrderInfo(orderId,value);
        return map;
    }
}
