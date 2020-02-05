package com.official.service;

import com.github.pagehelper.PageInfo;
import com.official.bean.Logger;
import com.official.bean.dto.LoggerDto;
import com.official.bean.dto.PageDto;

public interface LoggerService {
	int deleteByPrimaryKey(Integer id);

    int insert(Logger record);

    Logger selectByPrimaryKey(Integer id);

    PageInfo<LoggerDto>   selectAll(Integer userId,String keyword,PageDto pageDto);

    int updateByPrimaryKey(Logger record);
}
