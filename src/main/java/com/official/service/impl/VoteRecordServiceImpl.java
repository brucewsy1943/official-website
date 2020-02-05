package com.official.service.impl;

import com.official.bean.VoteRecord;
import com.official.dao.VoteRecordMapper;
import com.official.service.VoteRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("voteRecordService")
public class VoteRecordServiceImpl implements VoteRecordService {
    @Autowired
    private VoteRecordMapper voteRecordMapper;

    /**
     *
     * @param voteRecord
     * @return
     */
    @Override
    public Integer insertRecord(VoteRecord voteRecord) {
        Integer record = voteRecordMapper.insert(voteRecord);
        return record;
    }

    /**
     * 判断是否投过票
     * @param loginId
     * @return
     */
    @Override
    public boolean isEmployeeVoted(Integer loginId) {
        Integer voteRecord = voteRecordMapper.selectVoteRecord(loginId);
        if(voteRecord == 0){
            return  false;
        }
        return true;
    }
}
