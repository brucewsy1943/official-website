package com.official.service.impl;

import com.official.bean.dto.LeaveMessageDto;
import com.official.dao.LeaveMessageMapper;
import com.official.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2020/3/18.
 */
@Service("leaveMessageService")
public class LeaveMessageServiceImpl implements LeaveMessageService{

    @Autowired
    private LeaveMessageMapper leaveMessageMapper;

    @Override
    public void addMessage(LeaveMessageDto leaveMessageDto) {
        leaveMessageMapper.insert(leaveMessageDto);
    }
}
