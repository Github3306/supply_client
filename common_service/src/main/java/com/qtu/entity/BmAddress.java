package com.qtu.entity;

import lombok.Data;

@Data
public class BmAddress {
    private Integer addId;

    private Integer sellerId;

    private String contacter;

    private String tel;

    private String mainAddress;

    private String address;

    private Integer isDefault;

    private Integer isDeleted;

    private String lng;

    private String lat;
}