package com.qtu.product_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 封装物流信息
 * @author 张显
 * @create 2020-01-14 11:03
 */
@Data
public class LogisticsBean {

    /*    bm_inorderlist表     */
    private String orderId;//订单id
    private Integer sellerId; //店铺id-----------------客户商家id
    private BigDecimal deliveryFee; //配送费
    private BigDecimal amount; //订单金额
    private Integer payType; //支付方式 1.货到付款 2.在线支付
    //订单步骤 待支付=1 已支付，待配货=2 已配货，待验货=3 已验货，待装货=4 已装货，待发货=5，已发货，待交货=6 已交货=7
    //订单步骤   1= '未支付',2='待配货',3='配货中',4='已配货',5='待装货',6='装货中',7='已装货',8='待验收',9='验收中',
    // 10='已验收',11='待交货',12='交货中',13='已交货'
    private Integer orderStep;
    private String deliveryAddId; //配送地址id
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date createTime;//订单创建时间
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date payTime; //支付时间
    private Integer orderState; //订单状态 已取消=0 正常订单=1 已经完成=2 取消中=3 超时=4
    private String orderStepRecord; //订单配送步骤记录
    private Integer warehouserId; //仓库管理人员id
    private Integer logisticsId; //物流人员id
    private String mark; //订单备注
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date endTime;

    /*     bm_inordergoodslist表     */
    private Integer goodsId;//商品id
    private Integer spId;//规格id
    private String sname;//规格名称
    private String goodsName;//商品名称
    private String goodsImg;//商品图片
    private Integer buyNum;//购买数量
    private BigDecimal price;//购买金额
    private BigDecimal totalPrice;//总金额
    private Integer pickingNum;//配货数量
    private Integer loadingNum;//装货数量
    private Integer examineNum;//验货数量
    private Integer deliveryNum;//交货数量

    /*   bm_address表       */
    //private Integer addId;//地址id
    private String contacter;//联系人
    private String tel;//联系电话
    private String mainAddress;//大概地址
    private String address;//主要地址


}
