package com.official.service.impl;

import java.util.List;

import com.official.bean.Role;
import com.official.bean.dto.PermissionDto;
import com.official.bean.dto.RoleDto;
import com.official.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.official.dao.RoleMapper;

@Service
public class RoleServiceImpl  implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	

	@Override
	public List<Role> selectAll() {
		
		return roleMapper.selectAll();
	}

	@Transactional
	@Override
	public int insert(Role record) {
		Integer n = roleMapper.insert(record);
		return n;
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
	     int n = roleMapper.deleteByPrimaryKey(id);
		return n;
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(Role record) {
		
		return roleMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<PermissionDto> getPermissions(Integer roleId, Integer style) {
		
		return roleMapper.getPermissions(roleId,style);
	}

	@Override
	public int selectIdByName(String roleName) {
		
		return roleMapper.selectIdByName(roleName);
	}

	@Override
	public RoleDto selectByPrimaryKey(Integer id) {
		
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<PermissionDto> getPermissionsNoStyle(Integer roleId) {
		
		return roleMapper.getPermissionsNoStyle(roleId);
	}

	@Override
	public List<Role> fuzzySearch(String keyword) {
		
		return roleMapper.fuzzySearch(keyword);
	}

	

}
