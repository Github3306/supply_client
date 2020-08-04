package com.qtu.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BmMember {
    private Integer id;

    private String businessName;

    private BigDecimal deliveryFee;

    private BigDecimal deliveryRadius;

    private String address;

    private String backImg;

    private String mapCode;

    private Integer state;

    private String tel;

    private String password;

    private String payPassword;

    private String trueName;

    private String shopType;

    private String token;

    private BigDecimal amount;

    private Date addTime;

    private Integer isDeleted;

    private Integer aduit;

    private Integer business;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public BigDecimal getDeliveryRadius() {
        return deliveryRadius;
    }

    public void setDeliveryRadius(BigDecimal deliveryRadius) {
        this.deliveryRadius = deliveryRadius;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getBackImg() {
        return backImg;
    }

    public void setBackImg(String backImg) {
        this.backImg = backImg;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getAduit() {
        return aduit;
    }

    public void setAduit(Integer aduit) {
        this.aduit = aduit;
    }

    public Integer getBusiness() {
        return business;
    }

    public void setBusiness(Integer business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return "BmMember{" +
                "id=" + id +
                ", businessName='" + businessName + '\'' +
                ", deliveryFee=" + deliveryFee +
                ", deliveryRadius=" + deliveryRadius +
                ", address='" + address + '\'' +
                ", backImg='" + backImg + '\'' +
                ", mapCode='" + mapCode + '\'' +
                ", state=" + state +
                ", tel='" + tel + '\'' +
                ", password='" + password + '\'' +
                ", trueName='" + trueName + '\'' +
                ", shopType='" + shopType + '\'' +
                ", token='" + token + '\'' +
                ", amount=" + amount +
                ", addTime=" + addTime +
                ", isDeleted=" + isDeleted +
                ", aduit=" + aduit +
                ",business=" + business +
                '}';
    }
}