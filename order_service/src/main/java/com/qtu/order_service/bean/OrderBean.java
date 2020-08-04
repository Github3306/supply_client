package com.qtu.order_service.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author swl
 * @create 2020-01-10 10:51
 */
@Data
public class OrderBean {
    private String orderId;

    private Integer sellerId; //客户商家id

    private BigDecimal deliveryFee; //配送费

    private BigDecimal amount; //订单金额

    private Integer payType; //支付方式 1.货到付款 2.在线支付

    private Integer orderStep;//订单步骤 待支付=1 已支付，待配货=2 已配货，待验货=3 已验货，待装货=4 已装货，待发货=5，已发货，待交货=6 已交货=7

    private String deliveryAddId; //配送地址id

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date payTime; //支付时间

    private Integer orderState; //订单状态 已取消=0 正常订单=1 已经完成=2 取消中=3 超时=4

    private Integer warehouserId; //仓库管理人员id

    private Integer logisticsId; //物流人员id

    private String mark; //订单备注

    private String orderStepRecord; //订单配送步骤记录

    private String goodsName; //商品名称

    private String goodsImg; //商品图片

    private Integer buyNum; //购买数量

    private BigDecimal price; //购买金额

    private String contacter;//收货姓名

    private String tel; //电话

    private String address;//地址
}
