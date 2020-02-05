package com.official.controller;


import com.official.service.VoteRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voteRecord")
public class VoteRecordServiceController {

    @Autowired
    private VoteRecordService voteRecordService;

}
