package com.qtu.order_service;

import com.qtu.entity.BmGoods;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Hu Shengkai
 * @create 2020-01-10 22:02
 */
public class Test01 {

    @Test
    public void test1(){
        System.out.println(BmGoods.class.getName());
    }

    @Test
    public void test2(){
        String orderId = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(orderId);
    }
}
