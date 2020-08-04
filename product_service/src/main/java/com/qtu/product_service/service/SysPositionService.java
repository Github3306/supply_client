package com.qtu.product_service.service;

import com.qtu.entity.SysPosition;

import java.util.List;

/**
 * @author 张显
 * @create 2020-01-14 9:40
 */
public interface SysPositionService {
    /*得到所有岗位信息*/
    List<SysPosition> getSelectAllPositionInfo();
}
