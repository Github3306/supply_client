package com.qtu.order_service.controller;

import com.qtu.entity.BmGoods;
import com.qtu.entity.BmMember;
import com.qtu.order_service.bean.OrderBean;
import com.qtu.order_service.client.RedisFeignClient;
import com.qtu.order_service.entity.CheckOutBean;
import com.qtu.order_service.service.OrderBeanService;
import com.qtu.order_service.service.OrderService;
import com.qtu.util.CacheResult;
import com.qtu.util.ExceptionUtil;
import com.qtu.util.JsonUtils;
import com.qtu.util.MyResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hu Shengkai
 * @create 2020-01-07 11:09
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderBeanService orderBeanService;
    @Resource
    RedisFeignClient redisFeignClient;
    @Resource
    OrderService orderService;
    private static  final String MEMBER_TOKEN = "MEMBER_TOKEN:";

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
        List<OrderBean> orderList = orderBeanService.getOrderList(token, orderStep);
        return orderList;
    }
    @GetMapping("orderDetail")
    @ResponseBody
    public Object orderDetail(String orderId){
        System.out.println("进入到orderDetail()方法");
        System.out.println("接收的orderId是："+orderId);
        Map<String, Object> map = orderBeanService.getOrderDet(orderId);
        return map;
    }
    @GetMapping("orderDel")
    @ResponseBody
    public Object orderDel(String orderId){
        System.out.println("进入到orderDel()方法");
        System.out.println("接收的orderId是："+orderId);
        Map<String, Object> map = orderBeanService.updateOrder(orderId);
        return map;
    }

    @PostMapping("/creatOrder")
    @ResponseBody
    public MyResult creatOrder(@RequestParam("token") String token, HttpServletRequest request , String addressId ,
                               String remark , String checkOutBean) {

        MyResult result = null;
        CheckOutBean bean = JsonUtils.jsonToPojo(checkOutBean, CheckOutBean.class);
        try {
            result = orderService.createOrder(MEMBER_TOKEN+token, request, addressId, remark, bean);
        } catch (Exception e) {
            e.printStackTrace();
            result = MyResult.build(400, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }
}
