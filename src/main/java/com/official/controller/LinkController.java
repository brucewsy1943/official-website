package com.official.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.official.bean.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.official.bean.Link;
import com.official.bean.dto.LinkDto;
import com.official.exception.CustomException;
import com.official.service.LinkService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/link")
@RestController
public class LinkController {
        
	@Autowired
	private LinkService linkService;
   
	 @ApiOperation(value="新增链接",notes="新增链接")
	 @ApiImplicitParam(name = "linkDto", value = "链接对象", required = true, dataType = "LinkDto")
	 @PostMapping("/create")
	 public ResponseBean create(@RequestBody LinkDto linkDto) {
		 Link link = new Link();
		 link.setPubtimetype(linkDto.getPubtimetype());
		 link.setExpiredTimeType(linkDto.getExpiredTimeType());
		if (linkDto.getPubtimetype() == 1) {
			link.setPubtime(Timestamp.valueOf(linkDto.getPubtimes()));
		}else {
			link.setPubtime( new Timestamp(new Date().getTime()));
		}
		if (linkDto.getExpiredTimeType() == 1) {	
			link.setExpiredtime(Timestamp.valueOf(linkDto.getExpiredtimes()));
		}else {
			link.setExpiredtime(Timestamp.valueOf("2037-09-01 00:00:00"));
		}
		
		link.setIsshow(linkDto.getIsshow());
		link.setLinkname(linkDto.getLinkname());
		link.setLinkposition(linkDto.getLinkposition());
		link.setLinktype(linkDto.getLinktype());
		link.setLinkurl(linkDto.getLinkurl());
		if (linkDto.getOrderid()==0) {
			link.setOrderid(99);
		}else {
			link.setOrderid(linkDto.getOrderid());
		}
		link.setPicturepath(linkDto.getPicturepath());
		link.setRemark(linkDto.getRemark());
		link.setStatus(linkDto.getStatus());
		int n = linkService.insert(link);
		if (n<=0) {
			throw new CustomException("新增连接失败!");
		}
		return new ResponseBean(true, 200, "[insert a link successfully]", null);
		
	}
	
	   
	 @ApiOperation(value="查询全部链接",notes="查询全部链接")
	 @ApiImplicitParam(name = "status", value = "状态码", required = true, dataType = "java.lang.Integer")
	 @PostMapping("/selectAll")
	 public ResponseBean selectAll(Integer status) {
		List<Link> links = linkService.selectAll(status);
		return new ResponseBean(true, 200, "[insert a position successfully]",links );
		
	}
	 
	 
	 
	 @ApiOperation(value="根据id查询链接信息",notes="根据id查询链接信息")
	 @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "java.lang.Integer")
	 @PostMapping("/getInfo")
	 public ResponseBean getInfo(Integer id) {
		Link link = linkService.selectByPrimaryKey(id);
		return new ResponseBean(true, 200, "[insert a position successfully]",link );
		
	 }
	 
	 
	 @ApiOperation(value="更新链接",notes="更新链接")
	 @ApiImplicitParam(name = "linkDto", value = "链接对象", required = true, dataType = "LinkDto")
	 @PostMapping("/update")
	 public ResponseBean update(@RequestBody LinkDto linkDto) {
		 Link link = new Link();
		 link.setPubtimetype(linkDto.getPubtimetype());
		 link.setExpiredTimeType(linkDto.getExpiredTimeType());
		if (linkDto.getPubtimetype() == 1) {
			link.setPubtime(Timestamp.valueOf(linkDto.getPubtimes()));
		}else {
			link.setPubtime( new Timestamp(new Date().getTime()));
		}
		if (linkDto.getExpiredTimeType() == 1) {
			link.setExpiredtime(Timestamp.valueOf(linkDto.getExpiredtimes()));
		}else {
			link.setExpiredtime(Timestamp.valueOf("2037-09-01 00:00:00"));
		}
		link.setIsshow(linkDto.getIsshow());
		link.setLinkname(linkDto.getLinkname());
		link.setLinkposition(linkDto.getLinkposition());
		link.setLinktype(linkDto.getLinktype());
		link.setLinkurl(linkDto.getLinkurl());
		if (linkDto.getOrderid()==0) {
			link.setOrderid(99);
		}else {
			link.setOrderid(linkDto.getOrderid());
		}
		link.setPicturepath(linkDto.getPicturepath());
		link.setRemark(linkDto.getRemark());
		link.setStatus(linkDto.getStatus());
		link.setId(linkDto.getId());
		int n = linkService.updateByPrimaryKey(link);
		if (n<=0) {
			throw new CustomException("更新连接失败!");
		}
		return new ResponseBean(true, 200, "[update a link successfully]", null);
		
	}
	 
	 
	 @ApiOperation(value="删除链接",notes="删除链接")
	 @ApiImplicitParam(name = "id", value = "链接对象id数组", required = true, dataType = "java.lang.String")
	 @PostMapping("/delete")
	 public ResponseBean delete(String id) {
		//获取所有的id
	     String [] ids  =  id.split(",");
	     List<Integer> integers = new ArrayList<>();
	     for (String string : ids) {
		      int num =	Integer.valueOf(string);
		      integers.add(num);
		}
	     for (Integer integer : integers) {
			int n = linkService.deleteByPrimaryKey(integer);
			if (n<=0) {
				throw new CustomException("删除链接失败");
			}
		}
		return new ResponseBean(true, 200, "[delete a link successfully]", null);
		
	}
	 
	 
	 
	 @ApiOperation(value="关键字查询链接",notes="关键字查询链接")
	 @ApiImplicitParams({
		 @ApiImplicitParam(name = "status", value = "状态码", required = false, dataType = "java.lang.Integer"),
		 @ApiImplicitParam(name = "keyword", value = "关键字", required = false, dataType = "java.lang.String")
	 })
	 @PostMapping("/fuzzySearch")
	 public ResponseBean fuzzySearch(Integer status,String keyword) {
		List<Link> links =linkService.fuzzySearch(status, keyword);
		return new ResponseBean(true, 200, "[fuzzysearching positions successfully]",links );
		
	}
	 
	 
}
