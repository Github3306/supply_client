package com.qtu.entity;

import java.math.BigDecimal;

public class BmWarehouse {
    private Integer id;

    private String warehouseCode;

    private Integer mechanismid;

    private Integer warehouseType;

    private String address;

    private BigDecimal deliveryFeeAveryKM;

    private String startTime;

    private String endTime;

    private BigDecimal deliveryRadius;

    private String mapCode;

    private String tel;

    private Integer startSendNum;

    private Integer isDeleted;

    private Integer warehouseStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode == null ? null : warehouseCode.trim();
    }

    public Integer getMechanismid() {
        return mechanismid;
    }

    public void setMechanismid(Integer mechanismid) {
        this.mechanismid = mechanismid;
    }

    public Integer getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(Integer warehouseType) {
        this.warehouseType = warehouseType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public BigDecimal getDeliveryFeeAveryKM() {
        return deliveryFeeAveryKM;
    }

    public void setDeliveryFeeAveryKM(BigDecimal deliveryFeeAveryKM) {
        this.deliveryFeeAveryKM = deliveryFeeAveryKM;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public BigDecimal getDeliveryRadius() {
        return deliveryRadius;
    }

    public void setDeliveryRadius(BigDecimal deliveryRadius) {
        this.deliveryRadius = deliveryRadius;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode == null ? null : mapCode.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Integer getStartSendNum() {
        return startSendNum;
    }

    public void setStartSendNum(Integer startSendNum) {
        this.startSendNum = startSendNum;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getWarehouseStatus() {
        return warehouseStatus;
    }

    public void setWarehouseStatus(Integer warehouseStatus) {
        this.warehouseStatus = warehouseStatus;
    }
}