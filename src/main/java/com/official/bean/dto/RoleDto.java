package com.official.bean.dto;

import com.official.bean.Role;

public class RoleDto extends Role{
     
	 private String permissions;

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
}
