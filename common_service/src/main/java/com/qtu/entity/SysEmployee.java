package com.qtu.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class SysEmployee {
    private Integer id;

    private String employeeCode;

    private String ename;

    private String warehouseCode;

    private String tel;

    private Integer sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    private String password;

    private String token;

    private String positionId;

    private String imageUrl;

    private Date createTime;

    private Integer isDeleted;

}