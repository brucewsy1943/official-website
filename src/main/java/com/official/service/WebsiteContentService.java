package com.official.service;

import java.util.List;

import com.official.bean.dto.WebsiteContentDto;

import com.official.bean.WebsiteContent;

public interface WebsiteContentService {
	 int deleteByPrimaryKey(Integer id);

	    int insert(WebsiteContent record);

	    WebsiteContent selectByPrimaryKey(Integer id);

	    List<WebsiteContent> selectAll();

	    int updateByPrimaryKey(WebsiteContent record);
	    
	    //内容分析数据
	    List<WebsiteContentDto> contentAnalysis(String startDate, String endDate);
	    
	    //栏目分析数据
	    List<WebsiteContentDto> columnAnalysis(String startDate,String endDate);
}
