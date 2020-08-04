package com.qtu.product_service.bean;

import java.util.Date;

/**
 * @author chb
 * @date 2020/1/10 - 18:59
 **/
//bmb.bname ppName,
//        bmi.buyNum buyNum,
//        bms.sname ggName,
//        bms.id ggId,
//        bms.goodsImgUrl image,
//        bms.price price
//        bmc.cname flName,
//        sysm.mechanismName jgName
public class search {
    private Integer ggId;
    private String ppName;//品牌名称
    private Integer buyNum;
    private String ggName;
    private String image;
    private Double price;
    private String flName;
    private String jgName;
    private Integer hotShopping;
    private Date  dates;

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public Integer getHotShopping() {
        return hotShopping;
    }

    public void setHotShopping(Integer hotShopping) {
        this.hotShopping = hotShopping;
    }

    public String getFlName() {
        return flName;
    }

    public void setFlName(String flName) {
        this.flName = flName;
    }

    public String getJgName() {
        return jgName;
    }

    public void setJgName(String jgName) {
        this.jgName = jgName;
    }

    public Integer getGgId() {
        return ggId;
    }

    public void setGgId(Integer ggId) {
        this.ggId = ggId;
    }

    public String getPpName() {
        return ppName;
    }

    public void setPpName(String ppName) {
        this.ppName = ppName;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public String getGgName() {
        return ggName;
    }

    public void setGgName(String ggName) {
        this.ggName = ggName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
