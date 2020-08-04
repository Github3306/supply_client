package com.qtu.product_service.service.impl;

import com.qtu.product_service.client.RedisFeignClient;
import com.qtu.product_service.entity.LogisticsBean;
import com.qtu.product_service.entity.WuliuBean;
import com.qtu.product_service.mapper.LogisticsMapper;
import com.qtu.product_service.service.LogisticsService;
import com.qtu.util.CacheResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 张显
 * @create 2020-01-14 11:04
 */
@Service
@Transactional
public class LogisticsServiceImpl implements LogisticsService {

    @Resource
    private LogisticsMapper logisticsMapper;
    @Resource
    private RedisFeignClient zRedisFeignClient;

    /*根据状态码查询状态所对应的物流信息*/
    @Override
    public List<LogisticsBean> getSelectLogisticsStatus(Integer statusCode, Integer logisticsId) {
        /*从reids中获取信息*/
        /*CacheResult info = zRedisFeignClient.getFromRedis("LOGISTICS_STATUS_INFO");
        if(info.getData()!=null){
            List<LogisticsBean> list= (List<LogisticsBean>) info.getData();
            return list;
        }*/
        List<LogisticsBean> list = logisticsMapper.selectLogisticsStatus(statusCode, logisticsId);
        /*将信息存到redis中*/
//        zRedisFeignClient.setToRedis("LOGISTICS_STATUS_INFO",list,60*60L);
        return list;
    }

    /*根据订单id查询订单的物流信息*/
    @Override
    public List<WuliuBean> getSelectLogisticsInfoByOrderId(String orderId) {
        List<WuliuBean> wuliuInfo = logisticsMapper.selectLogisticsInfoByOrderId(orderId);
        return wuliuInfo;
    }

    /*更新装货物流状态*/
    @Override
    public int updateZhuangHuoStatus(Integer logisticsId, String orderId, String spId) {
        //分割字符串，取出商品规格id
        String[] split = spId.split(",");
        //定义循环表示
        int count =0;
        for (int i=0;i<split.length;i++){
            String sid=split[i];
            Integer ssid = Integer.valueOf(sid);
            logisticsMapper.updateZhuangHuoStatus(logisticsId, orderId, ssid);
            count++;
        }
        //如果全部装货数量全部更新
        if (count==split.length){
            //更新订单的状态码为开始交货
            logisticsMapper.updateStepCode(logisticsId,orderId,11);
            return 1;
        }
        return 0;
    }

    /*更新交货物流状态*/
    @Override
    public int updateJiaoHuoStatus(Integer logisticsId, String orderId, String spId) {
        //分割字符串，取出商品规格id
        String[] split = spId.split(",");
        //定义循环表示
        int count =0;
        for (int i=0;i<split.length;i++){
            String sid=split[i];
            Integer ssid = Integer.valueOf(sid);
            logisticsMapper.updateJiaoHuoStatus(logisticsId, orderId, ssid);
            count++;
        }
        //如果全部装货数量全部更新
        if (count==split.length){
            //更新订单的状态码为开始交货
            logisticsMapper.updateStepCode(logisticsId,orderId,13);
            return 1;
        }
        return 0;
    }

}
