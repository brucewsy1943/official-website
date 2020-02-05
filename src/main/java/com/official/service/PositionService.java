package com.official.service;

import java.util.List;

import com.official.bean.Position;

public interface PositionService {
	 int deleteByPrimaryKey(Integer id);

	   
	    int insert(Position record);

	    
	    Position selectByPrimaryKey(Integer id);

	    
	    List<Position> selectAll();

	   
	    int updateByPrimaryKey(Position record);
	    
	    
	    List<Position> fuzzySearch(String keyword);
	    
	    //根据位置类型来查询位置信息
	    List<Position> findPositionByType(Integer  positionType);
}
