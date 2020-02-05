package com.official.service;

import java.util.List;
import com.official.bean.Role_Permission;
public interface RolePermissionService {

    int deleteByPrimaryKey(Integer id);

    int deleteByRoleId(Integer roleId);
    
    int deleteByPerId(Integer perId);
    
    int insert(Role_Permission record);

    Role_Permission selectByPrimaryKey(Integer id);    
    
    List<Role_Permission> selectAll();

    int updateByPrimaryKey(Role_Permission record);
}
