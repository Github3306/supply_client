package com.qtu.product_service.bean;

/**
 * @author chb
 * @date 2020/1/9 - 19:39
 **/

public class SearchBoxBean {
    private Integer ppId; //品牌Id
    private String ppName;//品牌名称
    private Integer spId;//商品Id
    private String ssbh;//商品编号
    private String spckbh;//商品仓库编号
    private String spName;//商品名称
    private Integer bmiId;//顺序商品列表Id
    private String bmiName;
    private String image;
    private Integer buyNum;
    private Double price;

    public Integer getPpId() {
        return ppId;
    }

    public void setPpId(Integer ppId) {
        this.ppId = ppId;
    }

    public String getPpName() {
        return ppName;
    }

    public void setPpName(String ppName) {
        this.ppName = ppName;
    }

    public Integer getSpId() {
        return spId;
    }

    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    public String getSsbh() {
        return ssbh;
    }

    public void setSsbh(String ssbh) {
        this.ssbh = ssbh;
    }

    public String getSpckbh() {
        return spckbh;
    }

    public void setSpckbh(String spckbh) {
        this.spckbh = spckbh;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public Integer getBmiId() {
        return bmiId;
    }

    public void setBmiId(Integer bmiId) {
        this.bmiId = bmiId;
    }

    public String getBmiName() {
        return bmiName;
    }

    public void setBmiName(String bmiName) {
        this.bmiName = bmiName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
