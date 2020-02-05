package com.official.shiro;

import com.official.bean.Role;
import com.official.bean.dto.PermissionDto;
import com.official.jwt.JwtToken;
import com.official.service.RoleService;
import com.official.service.UserService;
import com.official.utils.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import com.official.bean.User;

/*
 * 自定义realm
 */
public class Myrealm extends AuthorizingRealm{
   
	@Autowired
    private UserService userService;
	
	@Autowired
    private RoleService roleService;
	
	
	/** * 大坑！，必须重写此方法，不然Shiro会报错
	 * */
	@Override 
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
		}
	
	//授权操作
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = JWTUtil.getUsername(principals.toString());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();//shiro提供了一个类，来存放权限和角色
		for(Role role : userService.getRoles(username) ){
			System.out.println(role.getRolename());
			  info.addRole(role.getRolename());
			  for(PermissionDto permission : roleService.getPermissions(role.getId(),1)){
				  System.out.println(permission.getCode());
				  info.addStringPermission(permission.getCode());
			  }
		}
		 	
		return info;
	}

	/**
	 * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。 
	*/ 
	@Override
	 protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException { 
	String token = (String) auth.getCredentials(); 
	// 解密获得username，用于和数据库进行对比 
	String username = JWTUtil.getUsername(token);
	if (username == null) { 
	throw new AuthenticationException("token invalid"); } 
	User userBean = userService.selectByName(username); 
	if (userBean == null) {
	 throw new AuthenticationException("User didn't existed!");
	 } 
	if (! JWTUtil.verify(token, username, userBean.getPassword())) {
	 throw new AuthenticationException("Username or password error");
	 } 
	return new SimpleAuthenticationInfo(token, token, "my_realm"); }
        
}
