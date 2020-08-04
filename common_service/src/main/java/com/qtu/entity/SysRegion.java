package com.qtu.entity;

import lombok.Data;

@Data
public class SysRegion {
    private Integer id;

    private String name;

    private Integer parent_id;

    private String short_name;

    private Integer level_type;

    private String city_code;

    private String zip_code;

    private String merger_name;

    private Float lng;

    private Float lat;

    private String pin_yin;

}