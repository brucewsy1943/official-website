package com.official.controller;

import java.util.ArrayList;
import java.util.List;

import com.official.bean.Position;
import com.official.bean.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.official.exception.CustomException;
import com.official.service.PositionService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/position")
@RestController
public class PositionController {
             
	@Autowired
	private PositionService positionService;
	
	 @ApiOperation(value="新增位置",notes="新增位置")
	 @ApiImplicitParam(name = "position", value = "位置对象", required = true, dataType = "Position")
	 @PostMapping("/create")
	 public ResponseBean create(@RequestBody Position position) {
		 if (position.getOrderId()==0) {
				position.setOrderId(99);
			}
		int n = positionService.insert(position);
		if (n<=0) {
			throw new CustomException("新增位置失败");
		}
		return new ResponseBean(true, 200, "[insert a position successfully]", null);
		
	}
	
	
     @ApiOperation(value="新增位置",notes="新增位置")
	 @PostMapping("/selectAll")
	 public ResponseBean selectAll() {
		List<Position> positions = positionService.selectAll();
		return new ResponseBean(true, 200, "[insert a position successfully]", positions);
		
	}
	
	
	 @ApiOperation(value="根据id获取位置信息",notes="根据id获取位置信息")
	 @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "java.lang.Integer")
	 @PostMapping("/getInfo")
	 public ResponseBean getInfo(Integer id) {
		Position positions = positionService.selectByPrimaryKey(id);
		return new ResponseBean(true, 200, "[get  the  position's information successfully]", positions);
		
	}
	 
	 
	 @ApiOperation(value="更新位置",notes="更新位置")
	 @ApiImplicitParam(name = "position", value = "位置对象", required = true, dataType = "Position")
	 @PostMapping("/update")
	 public ResponseBean update(@RequestBody Position position) {
		 if (position.getOrderId()==0) {
				position.setOrderId(99);
			}
		int n = positionService.updateByPrimaryKey(position);
		if (n<=0) {
			throw new CustomException("更新位置失败");
		}
		return new ResponseBean(true, 200, "[insert a position successfully]", null);
		
	}
	 
	 
	 @ApiOperation(value="删除位置",notes="删除位置")
	 @ApiImplicitParam(name = "id", value = "位置对象id数组", required = true, dataType = "java.lang.String")
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
			int n = positionService.deleteByPrimaryKey(integer);
			if (n<=0) {
				throw new CustomException("删除位置失败");
			}
		}
		return new ResponseBean(true, 200, "[delete a position successfully]", null);
		
	}
	 
	 
	 @ApiOperation(value="根据关键字查询位置信息",notes="根据关键字查询位置信息")
	 @ApiImplicitParam(name = "keyword", value = "关键字", required = true, dataType = "java.lang.String")
	 @PostMapping("/fuzzySearch")
	 public ResponseBean fuzzySearch(String keyword) {
	 	List<Position> positions = positionService.fuzzySearch(keyword);
	 return new ResponseBean(true, 200, "[insert a position successfully]", positions);
   }
	 
	 
	 @ApiOperation(value="根据位置类型查询位置信息",notes="根据位置类型查询位置信息")
	 @ApiImplicitParam(name = "positionType", value = "位置类型", required = true, dataType = "java.lang.Integer")
	 @PostMapping("/findPosition")
	 public ResponseBean findPosition(Integer  positionType) {
	 	List<Position> positions = positionService.findPositionByType(positionType);
	 return new ResponseBean(true, 200, "[insert a position successfully]", positions);
   }
	 
	 
}