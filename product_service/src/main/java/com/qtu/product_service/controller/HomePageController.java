package com.qtu.product_service.controller;

import com.qtu.product_service.service.HotProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 张的帅
 * @create 2019-10-23 19:52
 */
@RestController
@RequestMapping(value = "/homePage/")
public class HomePageController {

    @Autowired
    private HotProductService hotProductService;
    //查询首页的热门商品
    @GetMapping(value = "getHotProduct")
    public Object getHotProduct(){
        System.out.println("我进入了controller");
        return hotProductService.getHotproduct();
    }

    //查询商品的详细信息
    @GetMapping(value = "getHotProductInfo")
    public Object getHotProductInfo(@RequestParam("id") Integer id){
        System.out.println("我进入了getHotProductInfo"+id);
        return hotProductService.getHotProductInfo(id);
    }

    //查询所有商品的信息
    @GetMapping(value = "productInfo")
    public Object getProductInfo(Integer pageNo,Integer pageSize){
        System.out.println("我进入了productInfo");
        return hotProductService.getProductInfo(pageNo,pageSize);
    }

    //查询新品
    @GetMapping(value = "newProduct")
    public Object getNewProduct(){
        return hotProductService.getNewproduct();
    }

    //查询所有的品牌
    @GetMapping(value = "getBrand")
    public Object getBrand(){
        System.out.println("我进入了getBrand");
        return hotProductService.getBrand();
    }

    //根据品牌查询相应的商品
    @GetMapping("getInfoByCategory")
    public Object getInfoByCategory(@RequestParam("id") Integer id){
        System.out.println("我进入了getInfoByCategory");
        return hotProductService.getInfoByCategory(id);
    }
}
