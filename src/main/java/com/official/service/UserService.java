package com.official.service;

import java.util.List;

import com.official.bean.Role;
import com.official.bean.dto.UserDto;
import com.official.bean.User;

public interface UserService {

	int deleteByPrimaryKey(Integer id);


    int insert(User record);

   UserDto selectByPrimaryKey(Integer id);

  
    List<UserDto> selectAll();
    
    
   User selectByName(String username);
    
    List<Role> getRoles(String username);

    
    int updateByPrimaryKey(User record);
    
    List<UserDto> fuzzySearch(String keyword);
    
    List<User> checkName(String loginname);
    
}
