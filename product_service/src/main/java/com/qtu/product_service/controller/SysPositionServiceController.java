package com.qtu.product_service.controller;

import com.qtu.entity.SysPosition;
import com.qtu.product_service.service.SysPositionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张显
 * @create 2020-01-14 9:42
 */
@RestController
@RequestMapping(value = "/position")
public class SysPositionServiceController {

    @Resource
    private SysPositionService sysPositionService;

    /*得到所有岗位信息*/
    @GetMapping(value = "/getAll")
    public Object getAllPosition(String token){
        Map<String,Object>map=new HashMap<>();
        List<SysPosition> info = sysPositionService.getSelectAllPositionInfo();
        if(info!=null){
            map.put("position",info);
        }
        return map;
    }
}
