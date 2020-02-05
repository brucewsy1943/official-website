package com.official.service;

import java.util.List;

import com.official.bean.User_Role;


public interface User_RoleService {
	
	
	List<User_Role> selectAll();

	int updateByPrimaryKey(User_Role record);
	
	int insert(User_Role record);
	
	int deleteByPrimaryKey(Integer id);
	
	int deleteByUserId(Integer userId);
	
}
