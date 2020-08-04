package com.qtu.product_service.controller;

import com.qtu.product_service.service.StatisticsService;
import jdk.internal.dynalink.beans.StaticClass;
import jdk.nashorn.internal.runtime.Undefined;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张的帅
 * @create 2019-10-23 19:52
 */
/*
* 统计
* */
@RestController
@RequestMapping(value = "/Statistics/")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    //业绩统计---员工
    @GetMapping("info")
    public Object getInfo(HttpServletRequest request,Integer token, Integer orderStep,Integer condition){
        return statisticsService.getStatistics(request,token,orderStep,condition);
    }

    //业绩管理---仓库
    @GetMapping("ckinfo")
    public Object getCkInfo(HttpServletRequest request,Integer token){
        return statisticsService.getCk(request,token);
    }

    //业绩管理====客户
    @GetMapping("khinfo")
    public Object getKhInfo(HttpServletRequest request,Integer token,Integer deleted,Integer condition){
        return statisticsService.getKh(request,token,deleted,condition);
    }

    //业绩管理=====商品销售额
    @GetMapping("productCount")
    public Object getproductCount(){
        return statisticsService.getProductCount();
    }

    //业绩管理====更多销售额

    @PostMapping("productCount2")
    public Object getproductCount2(String token,String startTime,String endTime,Integer flagTime,Integer flag,Integer startRow,Integer endRow,Integer conditionTim){

        System.out.println("zswdfed================="+"token   "+
                token+"    startTime   "+startTime+"  endTime   "+ endTime+"  flagTime   "+flagTime+" flag "+flag+" startRow  "+ startRow+""+ endRow+"  conditionTim "+conditionTim);

        String time3 = null;
        String time4 = null;
        if(startTime != null && !startTime.equals("")){
            time3 = startTime;
            time4 = endTime;
        }
        if(startTime.equals("undefined")){
             time3 = null;
             time4 = null;
        }
        return statisticsService.getProductCount2(time3,time4,conditionTim);
    }

}
