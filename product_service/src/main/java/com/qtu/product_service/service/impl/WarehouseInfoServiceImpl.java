package com.qtu.product_service.service.impl;

import com.qtu.product_service.entity.WareHouseBean;
import com.qtu.product_service.mapper.WarehouseInfoMapper;
import com.qtu.product_service.service.WarehouseInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 张显
 * @create 2020-01-14 15:16
 */
@Service
public class WarehouseInfoServiceImpl implements WarehouseInfoService {

    @Resource
    private WarehouseInfoMapper warehouseInfoMapper;

    /*根据员工编号得到查询仓库信息*/
    @Override
    public WareHouseBean getSelectInfoByEmpCode(String employeeCode) {
        return warehouseInfoMapper.selectInfoByEmpCode(employeeCode);
    }
}
