package com.qtu.product_service.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 张的帅
 * @create 2019-10-23 19:52
 */

@Data
public class StatisticsBean {
    BigDecimal zmoney = BigDecimal.valueOf(0); //总收款
    BigDecimal cgmoney = BigDecimal.valueOf(0);//仓管人员钱
    BigDecimal wlmoney = BigDecimal.valueOf(0);//物流费
    Integer orderCount = 0;//订单量
    Integer buyNum = 0;//销售量
}
