package com.official.dao;

import com.official.bean.Role;
import com.official.bean.User;
import com.official.bean.dto.UserDto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(User record);


    UserDto selectByPrimaryKey(Integer id);
    
    User selectByName(String loginName);
    
    List<Role> getRoles(String loginName);
  
    List<UserDto> selectAll();

    
    int updateByPrimaryKey(User record);
    
    
    List<UserDto> fuzzySearch(String keyword);
    
    
    List<User> checkName(String loginname);
    
    
}
