package com.qtu.product_service.service;

import com.qtu.product_service.entity.ProductCountZswBean;
import com.qtu.product_service.entity.StatisticsBean;
import com.qtu.product_service.entity.Zbean;
import com.qtu.product_service.mapper.Zswmapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 张的帅
 * @create 2019-10-23 19:52
 */
//统计
public interface StatisticsService {
    //业绩管理----员工
    StatisticsBean getStatistics(HttpServletRequest request, Integer token, Integer orderStep, Integer condition);

    //业绩管理---仓库管理
    StatisticsBean getCk(HttpServletRequest request, Integer token);

    //业绩管理---客户管理
    StatisticsBean getKh(HttpServletRequest request, Integer token,Integer deleted,Integer condition);

    //业绩管理----商品销量
    List<ProductCountZswBean> getProductCount();

    //商品的详细订单信息
    List<Zbean> getProductCount2(String time3, String time4, Integer conditionTim);
}
