package com.official.service.impl;

import java.util.List;

import com.official.bean.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.official.dao.PositionMapper;
import com.official.service.PositionService;

@Service
public class PositionServiceImpl implements PositionService {
              
	  @Autowired
	  private PositionMapper positionMapper;

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return positionMapper.deleteByPrimaryKey(id);
	}

	@Transactional
	@Override
	public int insert(Position record) {
		
		return positionMapper.insert(record);
	}

	@Override
	public Position selectByPrimaryKey(Integer id) {
		
		return positionMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Position> selectAll() {
		
		return positionMapper.selectAll();
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(Position record) {
		
		return positionMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Position> fuzzySearch(String keyword) {
		
		return positionMapper.fuzzySearch(keyword);
	}

	@Override
	public List<Position> findPositionByType(Integer positionType) {
		
		return positionMapper.findPositionByType(positionType);
	}
	  
	  
	  
	  
}
