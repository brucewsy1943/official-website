package com.official.controller;

import com.official.bean.ResponseBean;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.official.bean.dto.LoggerDto;
import com.official.bean.dto.PageDto;
import com.official.service.LoggerService;

@RequestMapping("/logger")
@RestController
public class LoggerController {
               
	 @Autowired
	 private LoggerService loggerService;
	 
	    @RequiresAuthentication
		@RequestMapping("/selectAll")
		public ResponseBean sleectAll(Integer userId, String keyword, Integer pageNum, Integer pageSize){
		    PageDto pageDto = new PageDto();
		    pageDto.setPageNum(pageNum);
		    pageDto.setPageSize(pageSize);
			PageInfo<LoggerDto> loggerPageInfo = loggerService.selectAll(userId, keyword, pageDto);
			return new ResponseBean(true, 200,"[you are daidaishou]", loggerPageInfo);
			
		}
}
