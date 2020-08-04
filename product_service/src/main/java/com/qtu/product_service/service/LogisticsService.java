package com.qtu.product_service.service;

import com.qtu.product_service.entity.LogisticsBean;
import com.qtu.product_service.entity.WuliuBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 张显
 * @create 2020-01-14 11:04
 */
public interface LogisticsService {

    /*根据状态码查询状态所对应的物流信息*/
    List<LogisticsBean> getSelectLogisticsStatus(Integer statusCode, Integer logisticsId);

    /*根据订单id查询订单的物流信息*/
    List<WuliuBean> getSelectLogisticsInfoByOrderId(String orderId);

    /*更新装货物流状态*/
    int updateZhuangHuoStatus(Integer logisticsId, String orderId,String spId);

    /*更新交货物流状态*/
    int updateJiaoHuoStatus(Integer logisticsId, String orderId,String spId);

}
