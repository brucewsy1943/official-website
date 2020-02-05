package com.official.service;

import java.util.List;

import com.official.bean.dto.PermissionDto;
import com.official.bean.Role;
import com.official.bean.dto.RoleDto;


public interface RoleService {
	List<PermissionDto> getPermissions(Integer roleId, Integer style);
	
	List<Role> selectAll();
	
    RoleDto selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(Role record);
	
	int insert(Role record);
	
	int deleteByPrimaryKey(Integer id);
	
	 int selectIdByName(String roleName);
	 
	 List<Role>  fuzzySearch(String keyword);
	 
	 List<PermissionDto>  getPermissionsNoStyle(Integer roleId);
}
