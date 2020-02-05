package com.official.controller;

import java.util.List;

import com.official.bean.ResponseBean;
import com.official.bean.dto.ContentPubnish;
import com.official.bean.dto.WebsiteContentDto;
import com.official.bean.dto.WebsiteDto;
import com.official.bean.dto.WebsiteEcharts;
import com.official.service.WebsiteContentService;
import com.official.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/website")
@RestController
public class WebsiteController {
          
	@Autowired
	private WebsiteService websiteService;
	
	@Autowired
	private WebsiteContentService websiteContentService;
	
	 @ApiOperation(value="网站综合分析",notes="网站综合分析")
	 @PostMapping("/analysis")
	 public ResponseBean analysis() {
		WebsiteDto websiteDto = websiteService.comprehensiveAnalysis();
		return new ResponseBean(true, 200, "[comprehensive Analysis the website successfully]", websiteDto);
		
	}
	 
	 
	 @ApiOperation(value="网站综合分析图表",notes="网站综合分析图表")
	 @PostMapping("/echarts")
	 public ResponseBean echarts(String startDate,String endDate) {
		List<WebsiteEcharts> websiteDto = websiteService.echarts(startDate,endDate);
		return new ResponseBean(true, 200, "[comprehensive Analysis the website successfully]", websiteDto);
		
	}
	 
	 
	 @ApiOperation(value="网站内容分析",notes="网站内容分析")
	 @PostMapping("/contentAnalysis")
	 public ResponseBean content(String startDate,String endDate) {
		List<WebsiteContentDto> websiteContentDtos = websiteContentService.contentAnalysis(startDate,endDate);
		return new ResponseBean(true, 200, "[comprehensive Analysis the website successfully]", websiteContentDtos);
		
	}
	 
	 
	 @ApiOperation(value="网站内容分析",notes="网站内容分析")
	 @PostMapping("/columnAnalysis")
	 public ResponseBean column(String startDate,String endDate) {
		List<WebsiteContentDto> websiteContentDtos = websiteContentService.columnAnalysis(startDate,endDate);
		return new ResponseBean(true, 200, "[comprehensive Analysis the website successfully]", websiteContentDtos);
		
	}
	 
	 
	 @ApiOperation(value="内容发布分析",notes="内容发布分析")
	 @PostMapping("/contentPubnish")
	 public ResponseBean contentPubnish(String startDate,String endDate) {
		List<ContentPubnish> websiteContentDtos = websiteService.contentPubnish(startDate,endDate);
		return new ResponseBean(true, 200, "[comprehensive Analysis the website successfully]", websiteContentDtos);
		
	}
	 
	 
	 @ApiOperation(value="内容发布分析",notes="内容发布分析")
	 @PostMapping("/contentSource")
	 public ResponseBean contentSource(String startDate,String endDate) {
		List<ContentPubnish> websiteContentDtos = websiteService.contentSource(startDate,endDate);
		return new ResponseBean(true, 200, "[comprehensive Analysis the website successfully]", websiteContentDtos);
		
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
