package com.official.service.impl;

import java.util.List;

import com.official.bean.dto.PermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.official.bean.Permission;
import com.official.dao.PermissionMapper;
import com.official.service.PermissionService;


@Service
public class PermissionServiceImpl  implements PermissionService{

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public List<PermissionDto> selectAll() {
		
		return permissionMapper.selectAll();
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(Permission record) {
		
		return permissionMapper.updateByPrimaryKey(record);
	}

	@Transactional
	@Override
	public int insert(Permission record) {
		
		return permissionMapper.insert(record);
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return permissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PermissionDto selectById(Integer id) {
		
		return permissionMapper.selectById(id);
	}

	@Override
	public List<PermissionDto> selectSonPermissions(Integer id) {
		
		return permissionMapper.selectSonPermissions(id);
	}

	@Override
	public Permission selectByNavCode(String navCode) {
		
		return permissionMapper.selectByNavCode(navCode);
	}

	@Transactional
	@Override
	public int deleteByPId(Integer pId) {
		
		return permissionMapper.deleteByPId(pId);
	}
	
	

}
