package com.qtu.order_service;

import com.qtu.entity.BmGoods;
import com.qtu.order_service.client.RedisFeignClient;
import com.qtu.util.CacheResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceApplicationTests {
    @Autowired
    private RedisFeignClient redisFeignClient;

    @Test
    public void contextLoads() {
        //加入缓存
        CacheResult CacheResult = redisFeignClient.setToRedis("ttt", new BmGoods(), 10 *60 * 60l);//缓存时间为1小时
        System.out.println(CacheResult.getMsg());
    }

    @Test
    public void get() {
        CacheResult r = redisFeignClient.getFromRedis("ttt");
        System.out.println(r.getData());
        BmGoods date = (BmGoods) CacheResult.linkedHashMapToObject(r.getData(), BmGoods.class);
        System.out.println(date.toString());
    }

    @Test
    public void test1() {
        //加入缓存
        CacheResult result = redisFeignClient.setStringToRedis("qq", new String("20202020"), 60 * 60l);//缓存时间为1小时
        System.out.println(result.getMsg());
    }

    @Test
    public void test2() {
        //加入缓存
        CacheResult result = redisFeignClient.getFromRedis("qq");//缓存时间为1小时
        System.out.println(result.getMsg());
        String qq = (String) result.getData();
        System.out.println(qq);
    }

    @Test
    public void testSetList(){
        redisFeignClient.setToRedis("list1", Arrays.asList("123","sds","aaaa"),60*60L);
    }
    @Test
    public void getList() {
        CacheResult result = redisFeignClient.getFromRedis("list1");
        List<String> list = (List<String>) result.getData();
        System.out.println(list.toString());
    }


    @Test
    public void testSetList2(){
        List<BmGoods> list = new ArrayList<>();
        BmGoods g1 = new BmGoods();
        g1.setGoodsName("苹果");
        BmGoods g2 = new BmGoods();
        g2.setGoodsName("相机");
        BmGoods g3 = new BmGoods();
        g3.setGoodsName("电脑");
        list.add(g1);
        list.add(g2);
        list.add(g3);
        redisFeignClient.setToRedis("list2", list,60*60L);
    }
    @Test
    public void getList2() {
        CacheResult result = redisFeignClient.getFromRedis("list2");
        List<BmGoods> list = (List<BmGoods>) result.getData();
        System.out.println("集合打印"+list.toString());
    }

}
