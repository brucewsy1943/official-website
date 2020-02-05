package com.official.dao;

import com.official.bean.dto.WebsiteContentDto;
import com.official.bean.WebsiteContent;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface WebsiteContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebsiteContent record);

    WebsiteContent selectByPrimaryKey(Integer id);

    List<WebsiteContent> selectAll();

    int updateByPrimaryKey(WebsiteContent record);
    
    List<WebsiteContentDto> contentAnalysis(@Param("startDate")String startDate, @Param("endDate")String endDate);
    
    List<WebsiteContentDto> columnAnalysis(@Param("startDate")String startDate,@Param("endDate")String endDate);
}