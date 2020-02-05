package com.official.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.official.bean.ResponseBean;
import com.official.bean.dto.PermissionDto;
import com.official.service.LoggerService;
import com.official.service.RolePermissionService;
import com.official.service.RoleService;
import com.official.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.official.bean.Role;
import com.official.bean.Role_Permission;
import com.official.bean.User;
import com.official.bean.dto.RoleDto;
import com.official.exception.CustomException;
import com.official.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/role")
@RestController
public class RoleController {
         
	 private Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	
	   @Autowired
	   private RoleService roleService;
	   
	   @Autowired
	   private RolePermissionService rolePermissionService;
	   
	   @Autowired
	   private UserService userService;
	   
	   @Autowired
		private LoggerService loggerService;
	   
	   @ApiOperation(value="获取所有角色信息",notes="获取所有角色信息")
       @RequiresAuthentication
	   @PostMapping("/getRoles")
	   public ResponseBean getRoles(){
		   List<Role> roles = roleService.selectAll();   
		return new ResponseBean(true, 200, "[query all roles successfully]", roles);
		   
	   }
	   
	   @ApiOperation(value="新增角色信息",notes="新增角色信息")
	   @RequiresPermissions("role:create")
	   @PostMapping("/create")
	   public ResponseBean create(@RequestBody RoleDto roleDto,HttpServletRequest request){	
			 Subject subject = SecurityUtils.getSubject();
			 String token = (String)subject.getPrincipal();
			 String username = JWTUtil.getUsername(token);
			 User user3= userService.selectByName(username);
		   String [] ids = roleDto.getPermissions().split(",");
		   List<String> strings = new ArrayList<>();
		   //去除分隔后的空字符串
		   for (String string : ids) {
			if (!string.equals("")) {
				 strings.add(string);
			}
		}
		   List<Integer> permissions = new ArrayList<>();
		  for (String string : strings) {
			Integer integer = Integer.valueOf(string);
			permissions.add(integer);
		}
		   Role role = new Role();
		   role.setRolename(roleDto.getRolename());
		   if (roleDto.getOrderid() == 0) {
			   role.setOrderid(99);
		  }else {
			role.setOrderid(roleDto.getOrderid());
		}
		   
		   role.setRemark(roleDto.getRemark());
		   Integer n  = roleService.insert(role);
		   
		   if (n==0) {
			throw new CustomException("新增角色失败");
		 }
		   Integer id = roleService.selectIdByName(roleDto.getRolename());
		   for (Integer integer : permissions) {
			   Role_Permission role_Permission = new Role_Permission();
			   role_Permission.setPerid(integer);
			   role_Permission.setRoleid(id);
			  Integer num = rolePermissionService.insert(role_Permission);
			  if (num<0) {
				  throw new CustomException("新增角色权限失败");
			}
		}
		   //插入后插入日志表中
			com.official.bean.Logger loggers  = new com.official.bean.Logger();
			SimpleDateFormat  sdf  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Timestamp timestamp = Timestamp.valueOf(sdf.format(new Date()));
			loggers.setActionname("插入");
			loggers.setHappendate(timestamp);
			loggers.setIp(request.getRemoteAddr());
			loggers.setUserid(user3.getId());
			int result = loggerService.insert(loggers);
			if (result<=0) {
				throw new CustomException("插入日志表失败");
			}
		logger.info("新增角色成功");
 		return new ResponseBean(true, 200, "[query all roles successfully]", null);
		   
	   }
	   
	   @ApiOperation(value="获取角色信息",notes="根据id获取角色信息")
	   @RequiresPermissions("role:findOne")
	   @PostMapping("/getInfo")
	   public ResponseBean getInfo(Integer id){
		  RoleDto roleDto = roleService.selectByPrimaryKey(id);
		  List<PermissionDto> permissionDtos = roleService.getPermissionsNoStyle(id);
		  String permissions = "";
		   for (PermissionDto permissionDto : permissionDtos) {
			   permissions+=permissionDto.getId()+",";
		}
		   roleDto.setPermissions(permissions);
		return new ResponseBean(true,200,"[get the info successfully]", roleDto);	    
    }
	   
	   
	   @ApiOperation(value="更新角色信息",notes="更新角色信息")
	   @RequiresPermissions("role:update")
	   @PostMapping("/update")
	   public ResponseBean update(@RequestBody RoleDto roleDto,HttpServletRequest request){	   
		   Subject subject = SecurityUtils.getSubject();
	       String token = (String)subject.getPrincipal();
		   String username = JWTUtil.getUsername(token);
		   User user3= userService.selectByName(username);
		   String [] ids = roleDto.getPermissions().split(",");
		   List<String> strings = new ArrayList<>();
		   //去除分隔后的空字符串
		   for (String string : ids) {
			if (!string.equals("")) {
				 strings.add(string);
			}
		}
		   List<Integer> permissions = new ArrayList<>();
		  for (String string : strings) {
			Integer integer = Integer.valueOf(string);
			permissions.add(integer);
		}
		   Role role = new Role();
		   role.setRolename(roleDto.getRolename());
		   if (roleDto.getOrderid() == 0) {
			   role.setOrderid(99);
		  }else {
			role.setOrderid(roleDto.getOrderid());
		 } 
		   role.setRemark(roleDto.getRemark());
		   role.setId(roleDto.getId());
		   Integer n  = roleService.updateByPrimaryKey(role);
		     if (n==0) {
			throw new CustomException("更新角色失败");
		   }
		    //删除原来的权限列表
		   Integer result = rolePermissionService.deleteByRoleId(role.getId());
		   
		   if (result==0) {
			   throw new CustomException("删除原权限列表失败");
	   	 }
		  
		   //增加新的权限列表
		   for (Integer integer : permissions) {
			   Role_Permission role_Permission = new Role_Permission();
			   role_Permission.setPerid(integer);
			   role_Permission.setRoleid(role.getId());
			  Integer num = rolePermissionService.insert(role_Permission);
			  if (num<0) {
				  throw new CustomException("新增权限列表失败");
			}
		}
		   
		   //更新后插入日志表中
			com.official.bean.Logger loggers  = new com.official.bean.Logger();
			SimpleDateFormat  sdf  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Timestamp timestamp = Timestamp.valueOf(sdf.format(new Date()));
			loggers.setActionname("更新");
			loggers.setHappendate(timestamp);
			loggers.setIp(request.getRemoteAddr());
			loggers.setUserid(user3.getId());
			int result1 = loggerService.insert(loggers);
			if (result1<=0) {
				throw new CustomException("插入日志表失败");
			}
		logger.info("更新角色成功");
 		return new ResponseBean(true, 200, "[update the role successfully]", null);
		   
	   }
	   
	   
		 @ApiOperation(value="删除角色",notes="根据id删除角色")
		 @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "java.lang.String")
		 @RequiresPermissions("role:delete")
		 @PostMapping("/delete")
		 public ResponseBean delete(String id,HttpServletRequest request){	
			 Subject subject = SecurityUtils.getSubject();
		       String token = (String)subject.getPrincipal();
			   String username = JWTUtil.getUsername(token);
			   User user3= userService.selectByName(username);
			//获取所有的id
		     String [] ids  =  id.split(",");
		     List<Integer> integers = new ArrayList<>();
		     for (String string : ids) {
			      int num =	Integer.valueOf(string);
			      integers.add(num);
			}
		     for (Integer integer : integers) {
		    	 int n = roleService.deleteByPrimaryKey(integer);
					if (n<=0) {
						throw new CustomException("删除角色失败");
					}	
			}
		     
		     for (Integer integer : integers) {
				int n = rolePermissionService.deleteByRoleId(integer);
				if (n<=0) {
					throw new CustomException("删除角色下的权限列表失败");
				}
			}
		     		         
		        //更新后插入日志表中
				com.official.bean.Logger loggers  = new com.official.bean.Logger();
				SimpleDateFormat  sdf  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Timestamp timestamp = Timestamp.valueOf(sdf.format(new Date()));
				loggers.setActionname("删除");
				loggers.setHappendate(timestamp);
				loggers.setIp(request.getRemoteAddr());
				loggers.setUserid(user3.getId());
				int result1 = loggerService.insert(loggers);
				if (result1<=0) {
					throw new CustomException("插入日志表失败");
				}
				 logger.info("删除角色成功!");
				 return new ResponseBean(true, 200, "[delete the user successfully]", null);
			}
	   
		 
		 
		 @ApiOperation(value="根据关键字查询角色信息",notes="根据关键字查询角色信息")
		 @ApiImplicitParam(name = "keyword", value = "关键字", required = true, dataType = "java.lang.String")
		 @PostMapping("/fuzzySearch")
		 public ResponseBean fuzzySearch(String keyword) {
		 	List<Role> roles = roleService.fuzzySearch(keyword);
		 return new ResponseBean(true, 200, "[insert a position successfully]", roles);
	   }
		 
		 
	   
}