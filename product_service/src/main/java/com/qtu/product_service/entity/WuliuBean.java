package com.qtu.product_service.entity;

import lombok.Data;

/**
 * @author 张显
 * @create 2020-01-15 15:26
 */
@Data
public class WuliuBean {

    /*     bm_inordergoodslist表     */
    private Integer id;
    private String orderId;//订单号
    private Integer goodsId;//商品id
    private Integer spid;//商品详情id
    private String sname;//规格名称
    private String goodsName;//商品名称
    private String goodsImg;//商品图片

    /*     bm_goods表               */
    private String warehouseCode;//仓库编码

    /*     bm_inorderlist表               */
    private Integer orderStep;//物流步骤
    private Integer logisticsId;//物流人id

}
