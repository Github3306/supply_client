package com.qtu.product_service.controller;

import com.qtu.product_service.entity.WareHouseBean;
import com.qtu.product_service.service.WarehouseInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张显
 * @create 2020-01-14 15:19
 */
@RestController
@RequestMapping(value = "/warehouseInfo")
public class WarehouseInfoController {

    @Resource
    private WarehouseInfoService warehouseInfoService;

    /*根据员工编号得到查询仓库信息*/
    @GetMapping(value = "/getInfo")
    public Object getWarehouseInfoByEmpCode(String employeeCode){
        Map<String,Object>map=new HashMap<>();
        WareHouseBean infoByEmpCode = warehouseInfoService.getSelectInfoByEmpCode(employeeCode);
        if (infoByEmpCode!=null){
            map.put("infoByEmpCode",infoByEmpCode);
        }
        return map;
    }
}
