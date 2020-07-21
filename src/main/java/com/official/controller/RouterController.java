package com.official.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2020/4/26.
 */
@Controller
public class RouterController {
    @GetMapping("/")
    public String index(){
        return "index"; //当浏览器输入/index时，会返回 /templates/home.html页面
    }
}
