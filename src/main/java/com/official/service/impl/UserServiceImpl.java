package com.official.service.impl;

import java.util.List;

import com.official.bean.Role;
import com.official.bean.dto.UserDto;
import com.official.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.official.bean.User;
import com.official.dao.UserMapper;

@Service("userService")
public class UserServiceImpl implements UserService {
       
	@Autowired
	private UserMapper userMapper;
	
	

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		int n = userMapper.deleteByPrimaryKey(id);
		return n;
	}

	@Transactional
	@Override
	public int insert(User record) {
		int n = userMapper.insert(record);
		return n;
	}

	@Override
	public UserDto selectByPrimaryKey(Integer id) {
		
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserDto> selectAll() {
		
		return userMapper.selectAll();
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(User record) {
		int n = userMapper.updateByPrimaryKey(record);
		return n;
	}

	@Override
	public User selectByName(String username) {
		
		return userMapper.selectByName(username);
	}

	@Override
	public List<Role> getRoles(String username) {
		
		return userMapper.getRoles(username);
	}

	@Override
	public List<UserDto> fuzzySearch(String keyword) {
		
		return userMapper.fuzzySearch(keyword);
	}

	@Override
	public List<User> checkName(String loginname) {
		
		return userMapper.checkName(loginname);
	}
	
	
}
