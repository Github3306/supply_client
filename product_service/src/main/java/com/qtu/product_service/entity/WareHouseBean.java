package com.qtu.product_service.entity;

import lombok.Data;

/**
 * @author 张显
 * @create 2020-01-14 15:03
 */
@Data
public class WareHouseBean {
    private Integer id;//仓库id
    private String warehouseCode;//仓库编号
    private Integer mechanismid;//机构id
    private String address;
    private String employeeCode;//员工编号
    private String ename;//员工姓名
    private String mechanismName;//机构名称
}
