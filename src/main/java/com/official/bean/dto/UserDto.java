package com.official.bean.dto;

import com.official.bean.User;

public class UserDto extends User{
       
	  private String roleName;
	  
	  private String code;
	  
	public UserDto() {
		super();
	}

	public UserDto(String roleName, String code) {
		super();
		this.roleName = roleName;
		this.code = code;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
