package com.official.service.impl;

import java.util.List;

import com.official.bean.Link;
import com.official.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.official.dao.LinkMapper;

@Service
public class LinkServiceImpl implements LinkService {
	
	@Autowired
	private LinkMapper linkMapper;

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return linkMapper.deleteByPrimaryKey(id);
	}

	@Transactional
	@Override
	public int insert(Link record) {
		
		return linkMapper.insert(record);
	}

	
	@Override
	public Link selectByPrimaryKey(Integer id) {
		
		return linkMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Link> selectAll(Integer status) {
		
		return linkMapper.selectAll(status);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(Link record) {
		
		return linkMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Link> fuzzySearch(Integer status, String keyword) {
		
		return linkMapper.fuzzySearch(status, keyword);
	}

}
