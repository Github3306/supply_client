package com.qtu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysMechanism {
    private Integer id;

    private String mechanismCode;

    private String mechanismName;

    private Integer levelId;

    private String addvcd;

    private String password;

    private String tel;

    private Integer parentId;

    private Date createTime;

    private Integer isDeleted;

}