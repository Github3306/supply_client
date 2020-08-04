package com.qtu.product_service.mapper;

import com.qtu.product_service.entity.WareHouseBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WarehouseInfoMapper {

    /*根据员工编号查询仓库信息*/
    WareHouseBean selectInfoByEmpCode(String employeeCode);
}