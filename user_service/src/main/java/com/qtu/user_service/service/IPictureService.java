package com.qtu.user_service.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: lws
 * @create: 2019-11-28 20:23
 * @description: 图片上传
 */
public interface IPictureService {

    public Map uploadPicture(MultipartFile uploadFile);
}
