package com.qtu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Specification {
    private Integer id;

    private Integer goodsId;

    private String sname;

    private BigDecimal oldPrice;

    private BigDecimal price;

    private Integer stock;

    private Integer warnStock;

    private Integer allSaled;

    private String currentMonth;

    private Integer isShelves;

    private Integer isRecom;

    private String goodsImgUrl;

    private String qualityPeriod;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern ="yyyy-MM-dd")
    private Date productionDate;

    private String barCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getWarnStock() {
        return warnStock;
    }

    public void setWarnStock(Integer warnStock) {
        this.warnStock = warnStock;
    }

    public Integer getAllSaled() {
        return allSaled;
    }

    public void setAllSaled(Integer allSaled) {
        this.allSaled = allSaled;
    }

    public String getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(String currentMonth) {
        this.currentMonth = currentMonth == null ? null : currentMonth.trim();
    }

    public Integer getIsShelves() {
        return isShelves;
    }

    public void setIsShelves(Integer isShelves) {
        this.isShelves = isShelves;
    }

    public Integer getIsRecom() {
        return isRecom;
    }

    public void setIsRecom(Integer isRecom) {
        this.isRecom = isRecom;
    }

    public String getGoodsImgUrl() {
        return goodsImgUrl;
    }

    public void setGoodsImgUrl(String goodsImgUrl) {
        this.goodsImgUrl = goodsImgUrl == null ? null : goodsImgUrl.trim();
    }

    public String getQualityPeriod() {
        return qualityPeriod;
    }

    public void setQualityPeriod(String qualityPeriod) {
        this.qualityPeriod = qualityPeriod == null ? null : qualityPeriod.trim();
    }

    public Date getProductionDate() {
        return productionDate;
    }
    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
}