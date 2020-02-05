package com.official.service.impl;

import java.util.List;

import com.official.bean.Icon;
import com.official.dao.IconMapper;
import com.official.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IconServiceImpl implements IconService {
	
	@Autowired
	private IconMapper iconMapper;

	@Override
	public List<Icon> selectAll() {
		
		return iconMapper.selectAll();
	}

}
