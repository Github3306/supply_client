package com.qtu.entity;

import lombok.Data;

@Data
public class SysMechanismLevel {
    private Integer id;

    private String levelName;

    private Integer grade;

    private Integer parentId;

    private String mark;

    private Integer isDeleted;

}