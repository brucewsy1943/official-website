package com.official.controller;

import com.official.bean.ResponseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2020/3/13.
 */
@RestController
@RequestMapping("/contact")
public class ContactController {

    //添加留言
    @RequestMapping("/addMessage")
    public ResponseBean addMessage(){

        return null;
    }

    //查询所有的留言
    @RequestMapping("/messageList")
    public ResponseBean searchMessageList(){

        return null;
    }

}
