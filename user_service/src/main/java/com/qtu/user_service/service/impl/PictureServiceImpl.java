package com.qtu.user_service.service.impl;

import com.qtu.user_service.service.IPictureService;
import com.qtu.user_service.controller.CalanderController;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: lws
 * @create: 2019-11-28 20:25
 * @description:
 */
@Service
@Transactional
public class PictureServiceImpl implements IPictureService {

    @Override
    public Map uploadPicture(MultipartFile uploadFile) {
        Map<String,Object> resultMap = new HashMap<>();
        //生成一个新的文件名
        //取原始文件名
        String oldName = uploadFile.getOriginalFilename();
        //生成新的文件名
        long str = System.currentTimeMillis();
        String newName = str+oldName.substring(oldName.lastIndexOf("."));
        CalanderController cal = new CalanderController();

        String year = cal.getYear();
        String month = cal.getMonth();
        String day = cal.getDay();

        //上传文件
        try {
            File f = new File("I:\\images"+"\\"+year+"\\"+month+"\\"+day);
            if (!f.exists()){
                f.mkdirs();
            }
            f= new File("I:\\images"+"\\"+year+"\\"+month+"\\"+day+"\\"+newName);
            uploadFile.transferTo(f);
            resultMap.put("error",0);
            resultMap.put("url","http://images.qtu.com/images/"+year+"/"+month+"/"+day+"/" +newName);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error",1);
            resultMap.put("message","文件上传失败！");
        }

        return resultMap;
    }
}
