package com.qtu.order_service.entity;

import com.qtu.entity.BmMember;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单结算页面bean
 * @author Hu Shengkai
 * @create 2020-01-13 9:44
 */
@Data
public class CheckOutBean {
    private BigDecimal totalDeliveryFee; //配送费总额
    private BigDecimal totalPrice;  //总金额
    private List<CartItem> cartItemList; //购买的商品集合
}
