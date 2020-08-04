package com.qtu.user_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtu.user_service.service.IEmployeeService;
import com.qtu.user_service.service.IMemberService;
import com.qtu.user_service.service.IPictureService;
import com.qtu.user_service.sjyBean.UploadBean;
import com.qtu.util.JsonUtils;
import com.qtu.util.MyResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: lws
 * @create: 2019-11-28 20:41
 * @description: 上传下载
 */
@RestController
@RequestMapping(value = "/picture")
public class PictureController {

    @Resource
    private IPictureService pictureService;
    @Resource
    private IMemberService memberService;
    @Resource
    private IEmployeeService employeeService;

    @PostMapping(value = "/upload")
    public MyResult pictureUpload(@RequestParam("file") MultipartFile uploadFile,Integer id,String employeeCode){
        Map result = pictureService.uploadPicture(uploadFile);
        //转成json 适应多种浏览器
        ObjectMapper mapper = new ObjectMapper();
        String json = new String();
        try {
            json = mapper.writeValueAsString(result);
//            System.out.println(json);
            //{"error":0,"url":"http://images.qtu.com/images/2020/1/15/1579062476191.jpeg"}
            UploadBean uploadBean = JsonUtils.jsonToPojo(json, UploadBean.class);
//            System.out.println(uploadBean.getUrl());
            //http://images.qtu.com/images/2020/1/13/1578917938879.jpeg
            //将获取到的url存到数据库中
            if (id != null){
                memberService.updateSellerBackImg(uploadBean.getUrl(), id);
                return MyResult.ok(uploadBean.getUrl());
            }
            if (employeeCode != null){
                employeeService.updateEmployeeImageUrl(uploadBean.getUrl(),employeeCode);
                return MyResult.ok(uploadBean.getUrl());
            }
            return MyResult.build(502,"无效的请求");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return MyResult.build(500,"头像更改失败");
        }

    }


}
