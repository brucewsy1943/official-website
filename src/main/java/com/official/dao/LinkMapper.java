package com.official.dao;

import com.official.bean.Link;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface LinkMapper {
    
    int deleteByPrimaryKey(Integer id);

    
    int insert(Link record);

    
    Link selectByPrimaryKey(Integer id);

    
    List<Link> selectAll(Integer status);

    
    List<Link> fuzzySearch(@Param("status") Integer status,@Param("keyword") String keyword);
    
    int updateByPrimaryKey(Link record);
}