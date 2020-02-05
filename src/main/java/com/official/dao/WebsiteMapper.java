package com.official.dao;

import com.official.bean.Website;
import com.official.bean.dto.ContentPubnish;
import com.official.bean.dto.WebsiteDto;
import com.official.bean.dto.WebsiteEcharts;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface WebsiteMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Website record);

    Website selectByPrimaryKey(Integer id);

    List<Website> selectAll();

    int updateByPrimaryKey(Website record);
    
    Website selectDateByIp(String ip);
    //网站综合分析
    WebsiteDto comprehensiveAnalysis();
    
    //ip数和访问量图表显示
    List<WebsiteEcharts> echarts (@Param("startDate")String startDate,@Param("endDate")String endDate);
    
    //内容发布统计(按发布者统计)
    List<ContentPubnish> contentPubnish(@Param("startDate")String startDate, @Param("endDate")String endDate);
    
    //内容发布统计(按消息来源统计)
    List<ContentPubnish> contentSource(@Param("startDate")String startDate,@Param("endDate")String endDate);
    
}