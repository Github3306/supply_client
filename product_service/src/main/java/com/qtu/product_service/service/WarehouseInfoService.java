package com.qtu.product_service.service;

import com.qtu.product_service.entity.WareHouseBean;

/**
 * @author 张显
 * @create 2020-01-14 15:16
 */
public interface WarehouseInfoService {

    /*根据员工编号得到查询仓库信息*/
    WareHouseBean getSelectInfoByEmpCode(String employeeCode);
}
