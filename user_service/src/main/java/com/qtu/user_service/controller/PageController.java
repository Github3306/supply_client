package com.qtu.user_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Hu Shengkai
 * @create 2020-01-08 10:54
 */
@Controller
public class PageController {

    @RequestMapping("/{path}")
    public String page(@PathVariable("path") String path){
        return path;
    }
}
