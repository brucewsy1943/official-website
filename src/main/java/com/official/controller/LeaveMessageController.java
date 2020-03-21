package com.official.controller;

import com.official.bean.ResponseBean;
import com.official.bean.dto.LeaveMessageDto;
import com.official.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2020/3/18.
 */
@RestController
@RequestMapping("/leavemessage")
public class LeaveMessageController {

    @Autowired
    private LeaveMessageService leaveMessageService;

    @PostMapping("/add")
    public ResponseBean addMessage(LeaveMessageDto leaveMessageDto){

        leaveMessageService.addMessage(leaveMessageDto);

        return new ResponseBean(true, 200,"success",null);
    }

}
