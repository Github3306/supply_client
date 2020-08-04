package com.qtu.product_service.controller;

import com.qtu.product_service.entity.LogisticsBean;
import com.qtu.product_service.entity.WuliuBean;
import com.qtu.product_service.service.LogisticsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张显
 * @create 2020-01-14 19:10
 */
@RestController
@RequestMapping(value = "/logistics")
public class LogisticsController {

    @Resource
    private LogisticsService logisticsService;

    /**
     *根据状态码查询状态所对应的物流信息
     * @param token
     * @param statusCode     物流状态    1：装货    2：交货
     * @param logisticsId    物流人id
     * @return
     */
    @GetMapping(value = "/getLogisticsInfo")
    public Object getLogisticsStatus(String token,Integer statusCode, Integer logisticsId){
        Map<String,Object>map=new HashMap<>();
        List<LogisticsBean> list = logisticsService.getSelectLogisticsStatus(statusCode, logisticsId);
        if (list!=null){
            map.put("logisticsInfo",list);
        }
        return map;
    }

    /**
     * 根据订单id查询订单的物流信息
     * @param token
     * @param orderId
     * @return
     */
    @GetMapping(value = "/getLogisticsInfoByOId")
    public Object getLogisticsByOId(String token,String orderId){
        Map<String,Object>map=new HashMap<>();
        List<WuliuBean> info = logisticsService.getSelectLogisticsInfoByOrderId(orderId);
        if (info!=null){
            map.put("logisticsByOId",info);
        }
        return map;
    }


    /**
     * 更新装货数量
     * @param logisticsId
     * @param orderId
     * @param spId
     * @param step
     * @return
     */
    @PostMapping("/updateZhuangHuo")
    public Object updateZhuangHuoNum(Integer token,Integer logisticsId, String orderId, String spId,Integer step){
        Map<String,Object> map=new HashMap<>();
        //step等于1为更新装货数量
        if (step==1){
            int status = logisticsService.updateZhuangHuoStatus(logisticsId, orderId, spId);
            if (status>0){
                map.put("code",200);
            }else {
                map.put("code",400);
            }
        }else {
            map.put("code",500);
        }
        return map;
    }

    /**
     * 更新交货数量
     * @param logisticsId
     * @param orderId
     * @param spId
     * @param step
     * @return
     */
    @PostMapping("/updateJiaoHuo")
    public Object updateJiaoHuoNum(Integer token,Integer logisticsId, String orderId, String spId,Integer step){
        Map<String,Object> map=new HashMap<>();
        //step等于1为更新装货数量
        if (step==2){
            int status = logisticsService.updateJiaoHuoStatus(logisticsId, orderId, spId);
            if (status>0){
                map.put("code",200);
            }else {
                map.put("code",400);
            }
        }else {
            map.put("code",500);
        }
        return map;
    }
}
