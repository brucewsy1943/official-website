package com.official.service;

import java.util.List;

import com.official.bean.Column;
import com.official.bean.dto.ColumnDto;

public interface ColumnService {
	 /*
	  * 新增栏目
	  */
	 int insert(Column record);
	 
	 /*
	  * 更新栏目
	  */
	 int update(Column column);
	 
	 /*
	  * 根据id删除栏目
	  */
	 
	 int deleteById(Integer id);
	 
	 
	 /*
	  * 根据主键id查询栏目信息
	  */
	 ColumnDto selectById(Integer id);
	 
	 /*
	  * 根据主键id查询所有子节点id
	  */
	 List<Integer> selectSonId(Integer id);
	 
	 /*
	  * 查询所有的栏目信息
	  */
	 List<Column> getColumnsInfo();
	 
	 
	 /*
	  * 根据id获取所有子栏目信息
	  */
	 List<Column> findSonColumn(Integer id);
	 
	 
	 /*
	  * 根据栏目名称和父栏目id模糊查询栏目
	  */
	 List<Column> fuzzySearch(String keyword);
	 
	 /*
	  * 根据id查询父节点id
	  */
	 int selectParentId(Integer id);

	 
}
