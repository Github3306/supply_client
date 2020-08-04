package com.qtu.product_service.bean;

/**
 * @author chb
 * @date 2020/1/14 - 17:09
 **/
public class Shop {
//     dpName, dpImage,dpZuobiao, dpLeiXing,  ckName

    private String dpName;
    private String dpImage;
    private String dpZuobiao;
    private String dpLeiXing;
    private String ckName;
    private String ckZuobiao;
    private Double distance;
    private Double dpZbJd;
    private Double dpZbiaoWd;

    public Double getDpZbJd() {
        return dpZbJd;
    }

    public void setDpZbJd(Double dpZbJd) {
        this.dpZbJd = dpZbJd;
    }

    public Double getDpZbiaoWd() {
        return dpZbiaoWd;
    }

    public void setDpZbiaoWd(Double dpZbiaoWd) {
        this.dpZbiaoWd = dpZbiaoWd;
    }

    public String getCkZuobiao() {
        return ckZuobiao;
    }

    public void setCkZuobiao(String ckZuobiao) {
        this.ckZuobiao = ckZuobiao;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getDpName() {
        return dpName;
    }

    public void setDpName(String dpName) {
        this.dpName = dpName;
    }

    public String getDpImage() {
        return dpImage;
    }

    public void setDpImage(String dpImage) {
        this.dpImage = dpImage;
    }

    public String getDpZuobiao() {
        return dpZuobiao;
    }

    public void setDpZuobiao(String dpZuobiao) {
        this.dpZuobiao = dpZuobiao;
    }

    public String getDpLeiXing() {
        return dpLeiXing;
    }

    public void setDpLeiXing(String dpLeiXing) {
        this.dpLeiXing = dpLeiXing;
    }

    public String getCkName() {
        return ckName;
    }

    public void setCkName(String ckName) {
        this.ckName = ckName;
    }
}
