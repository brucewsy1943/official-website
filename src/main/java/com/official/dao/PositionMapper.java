package com.official.dao;

import com.official.bean.Position;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface PositionMapper {
    
    int deleteByPrimaryKey(Integer id);

   
    int insert(Position record);

    
    Position selectByPrimaryKey(Integer id);

    
    List<Position> selectAll();

   
    List<Position> fuzzySearch(String keyword);
    
    //根据位置类型来查询位置信息
    List<Position> findPositionByType(Integer  positionType);
    
    int updateByPrimaryKey(Position record);
}