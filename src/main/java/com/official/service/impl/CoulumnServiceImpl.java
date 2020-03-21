package com.official.service.impl;
import java.util.List;

import com.official.dao.ColumnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.official.bean.Column;
import com.official.service.ColumnService;

@Service
public class CoulumnServiceImpl implements ColumnService {
	
	
	@Autowired
	private ColumnMapper columnMapper;

	@Transactional
	@Override
	public int insert(Column record) {
		int n = columnMapper.insert(record);
		return n;
	}

	
	@Transactional
	@Override
	public int update(Column column) {
		int n = columnMapper.updateByPrimaryKey(column);
		return n;
	}

	@Transactional
	@Override
	public int deleteById(Integer id) {
		int n = columnMapper.deleteByPrimaryKey(id);
		return n;
	}

	@Override
	public Column selectById(Integer id) {
		Column column = columnMapper.selectByPrimaryKey(id);
		return column;
	}

	@Override
	public List<Integer> selectSonId(Integer id) {
		return columnMapper.selectSonId(id);
	}

	@Override
	public List<Column> getColumnsInfo() {
		
		return columnMapper.selectAll();
	}

	@Override
	public List<Column> findSonColumn(Integer id) {
		
		return columnMapper.findSonColumn(id);
	}

	@Override
	public List<Column> fuzzySearch(String keyword) {
		if (keyword == null || "".equals(keyword)){
			columnMapper.selectAll();
		}
		return columnMapper.fuzzySearch(keyword);
	}


	@Override
	public int selectParentId(Integer id) {
		
		return columnMapper.selectParentId(id);
	}

}
