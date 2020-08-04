package com.qtu.order_service.controller;

import com.qtu.order_service.service.PayService;
import com.qtu.util.ExceptionUtil;
import com.qtu.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hu Shengkai
 * @create 2020-01-14 16:31
 */
@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private PayService payService;

    @PostMapping("/goPay")
    public MyResult goPay(String orderId, Integer payType){
        MyResult myResult = null;
        try {
            myResult = payService.goPay(orderId, payType);
        } catch (Exception e) {
            e.printStackTrace();
            myResult.build(400, ExceptionUtil.getStackTrace(e));
        }
        return myResult;
    }
}
