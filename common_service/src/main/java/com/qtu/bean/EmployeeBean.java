package com.qtu.bean;

import lombok.Data;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: lws
 * @create: 2019-12-26 20:47
 * @description: emploee+wareHouse+岗位
 */
@Data
public class EmployeeBean {

    private String employeeCode;
    private String warehouseCode;
    private String tel;
    private String ename;
    private String sex;
    private Date birthday;
    private String positionName;
    private String imageUrl;

}
