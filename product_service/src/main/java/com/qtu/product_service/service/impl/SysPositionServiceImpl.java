package com.qtu.product_service.service.impl;

import com.qtu.entity.SysPosition;
import com.qtu.product_service.mapper.SysPositionMapper;
import com.qtu.product_service.service.SysPositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 张显
 * @create 2020-01-14 9:40
 */
@Service
public class SysPositionServiceImpl implements SysPositionService {

    @Resource
    private SysPositionMapper sysPositionMapper;

    /*得到所有岗位信息*/
    @Override
    public List<SysPosition> getSelectAllPositionInfo() {
        return sysPositionMapper.selectAllPositionInfo();
    }
}
