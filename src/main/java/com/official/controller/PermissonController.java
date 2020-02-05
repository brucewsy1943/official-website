package com.official.controller;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.official.bean.Logger;
import com.official.bean.ResponseBean;
import com.official.bean.Role;
import com.official.bean.dto.PermissionDto;
import com.official.service.LoggerService;
import com.official.service.RolePermissionService;
import com.official.tree.TreeUtil;
import com.official.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.official.bean.Permission;
import com.official.bean.User;
import com.official.exception.CustomException;
import com.official.service.PermissionService;
import com.official.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/permission")
@RestController
public class PermissonController {
      
	
	@Autowired
	private com.official.service.RoleService RoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private RolePermissionService rolePermissionService;
	
	@Autowired 
	private LoggerService loggerService;
	
	@RequestMapping("/getList")
	public ResponseBean getAll(){
		 Subject subject = SecurityUtils.getSubject();
		 String username = JWTUtil.getUsername(subject.getPrincipal().toString());
		 List<Role> roles = userService.getRoles(username);
		 List<PermissionDto> permissions = new ArrayList<>();
		 for (Role role : roles) {
			permissions = RoleService.getPermissions(role.getId(),0);
			
		}
	
		     //不同角色下的权限去重
			 for (int i = 0; i < permissions.size() - 1; i++) {
		            for (int j = permissions.size() - 1; j > i; j--) {
		                if (permissions.get(j).equals(permissions.get(i))) {
		                	permissions.remove(j);
		                }
		            }
		        }
			 
			
		List<PermissionDto> permission = new TreeUtil<PermissionDto>().getChildTree(permissions);
		
		return new ResponseBean(true, 200,"hahhaha", permission);
		
	}
	
	
	@RequiresPermissions("permission:create")
	@RequestMapping("/create")
	public ResponseBean create(@RequestBody PermissionDto permissionDto,HttpServletRequest request){
		 Subject subject = SecurityUtils.getSubject();
		 String token = (String)subject.getPrincipal();
		 String username = JWTUtil.getUsername(token);
		 User user3= userService.selectByName(username);
		Permission permission = new Permission();
		if (permissionDto.getP_navCode()==""||permissionDto.getP_navCode()==null) {
              permission.setStyle(0);
              permission.setDescription(permissionDto.getDescription());
              permission.setIconId(permissionDto.getIconId());
              permission.setNavCode(permissionDto.getNavCode());
              if (permissionDto.getOrderId() == 0) {
            	  permission.setOrderId(99);
			}else {
				 permission.setOrderId(permissionDto.getOrderId());
			}
              
	          int n = permissionService.insert(permission);
	          if (n<0) {
				throw new CustomException("新增权限失败!");
				}
	          else {  
	        	//插入后插入日志表中
	  			Logger loggers  = new Logger();
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
                 	return new ResponseBean(true, 200,"[insert a permission successfully]", null);			
				}
		}else {
			  
			  
			  String [] permissions = permissionDto.getCodes().split(",");
			  System.out.println(permissions[0]+"----");
			  if (permissions[0]=="" || (permissions.length==1&&permissions[0].equals("view"))) {
				  permission.setpId(permissionService.selectByNavCode(permissionDto.getP_navCode()).getId());
				  permission.setStyle(0);
				  permission.setDescription(permissionDto.getDescription());
				  permission.setIconId(permissionDto.getIconId());
				  permission.setNavCode(permissionDto.getNavCode());
	              permission.setOrderId(permissionDto.getOrderId());
	              permission.setUrl(permissionDto.getUrl());    
	              System.out.println(permissions[0]);
	              int n = permissionService.insert(permission); 
	              if (n<0) {
					throw new CustomException("新增权限失败!");
				}
	            //插入后插入日志表中
	  			Logger loggers  = new Logger();
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
	              return new ResponseBean(true, 200,"[insert a permission successfully]", null);			
			}else {
				  System.out.println("-------");
				  //如果codes不为空或者view,则先添加父权限,再添加选中的子权限
				  permission.setpId(permissionService.selectByNavCode(permissionDto.getP_navCode()).getId());
				  permission.setStyle(0);
				  permission.setDescription(permissionDto.getDescription());
				  permission.setIconId(permissionDto.getIconId());
				  permission.setNavCode(permissionDto.getNavCode());
				  if (permissionDto.getOrderId() == 0) {
	            	  permission.setOrderId(99);
				}else {
					 permission.setOrderId(permissionDto.getOrderId());
				}
	              permission.setUrl(permissionDto.getUrl());  
	              int n = permissionService.insert(permission); 
	              if (n<0) {
					throw new CustomException("新增权限失败!");
				 }
	              for (String string : permissions) {
	            	  Permission  permission2 = new Permission();          
		              if (!string.equals("view")) {
		            	permission2.setStyle(1);
						permission2.setCode(permissionDto.getNavCode().toLowerCase()+":"+string);
						if (string.equals("findAll")) {
							permission2.setDescription("显示全部");
						}
						if (string.equals("findOne")) {
							permission2.setDescription("显示详情");
						}
						if (string.equals("create")) {
							permission2.setDescription("新增");
						}
						if (string.equals("update")) {
							permission2.setDescription("编辑");
						}
						if (string.equals("delete")) {
							permission2.setDescription("删除");
						}
		                Integer orderId  = 20;
		                permission2.setpId(permissionService.selectByNavCode(permissionDto.getNavCode()).getId());
		                permission2.setNavCode(permissionDto.getNavCode()+string);
		                permission2.setOrderId(orderId);
		                int result = permissionService.insert(permission2);
		                if (result<0) {
							throw new CustomException("插入子权限列表失败");
						}
		                orderId = orderId+5;
		                
					}
		              
				}
	            //插入后插入日志表中
	  			Logger loggers  = new Logger();
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
	              return new ResponseBean(true, 200,"[insert a permission successfully]", null);			
			}
			}
		}
	
	
	@RequiresPermissions("permission:update")
	@RequestMapping("/update")
	public ResponseBean update(@RequestBody PermissionDto permissionDto,HttpServletRequest request){
		 Subject subject = SecurityUtils.getSubject();
		 String token = (String)subject.getPrincipal();
		 String username = JWTUtil.getUsername(token);
		 User user3= userService.selectByName(username);
		Permission permission = new Permission();
		if (permissionDto.getP_navCode()==""||permissionDto.getP_navCode()==null) {
              permission.setStyle(0);
              permission.setDescription(permissionDto.getDescription());
              permission.setIconId(permissionDto.getIconId());
              permission.setNavCode(permissionDto.getNavCode());
              permission.setUrl(permissionDto.getUrl());
              if (permissionDto.getOrderId() == 0) {
            	  permission.setOrderId(99);
			}else {
				 permission.setOrderId(permissionDto.getOrderId());
			}
              permission.setId(permissionDto.getId());
	          int n = permissionService.updateByPrimaryKey(permission);
	          if (n<0) {
				throw new CustomException("更新权限失败!");
				}
	          else {  
	        	//插入后插入日志表中
		  			Logger loggers  = new Logger();
		  			SimpleDateFormat  sdf  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  		    Timestamp timestamp = Timestamp.valueOf(sdf.format(new Date()));
		  			loggers.setActionname("更新");
		  			loggers.setHappendate(timestamp);
		  			loggers.setIp(request.getRemoteAddr());
		  			loggers.setUserid(user3.getId());
		  			int result = loggerService.insert(loggers);
		  			if (result<=0) {
		  				throw new CustomException("插入日志表失败");
		  			}
                 	return new ResponseBean(true, 200,"[update a permission successfully]", null);			
				}
		}else {  
            	  permission.setpId(permissionService.selectByNavCode(permissionDto.getP_navCode()).getId());
				  permission.setStyle(0);
				  permission.setDescription(permissionDto.getDescription());
				  permission.setIconId(permissionDto.getIconId());
				  permission.setNavCode(permissionDto.getNavCode());
				  if (permissionDto.getOrderId() == 0) {
	            	  permission.setOrderId(99);
				}else {
					 permission.setOrderId(permissionDto.getOrderId());
				}
	              permission.setUrl(permissionDto.getUrl());
	              permission.setId(permissionDto.getId());
	              int n = permissionService.updateByPrimaryKey(permission); 
	              if (n<0) {
					throw new CustomException("更新权限失败!");
				}
	            //插入后插入日志表中
		  			Logger loggers  = new Logger();
		  			SimpleDateFormat  sdf  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  		    Timestamp timestamp = Timestamp.valueOf(sdf.format(new Date()));
		  			loggers.setActionname("更新");
		  			loggers.setHappendate(timestamp);
		  			loggers.setIp(request.getRemoteAddr());
		  			loggers.setUserid(user3.getId());
		  			int result = loggerService.insert(loggers);
		  			if (result<=0) {
		  				throw new CustomException("插入日志表失败");
		  			}
	              return new ResponseBean(true, 200,"[insert a permission successfully]", null);			
			}
	              
			}
	
	

	 @ApiOperation(value="删除权限",notes="根据id删除权限")
	 @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "java.lang.String")
	 @RequiresPermissions("permission:delete")
	 @PostMapping("/delete")
	 public ResponseBean delete(String id,HttpServletRequest request){
		 Subject subject = SecurityUtils.getSubject();
		 String token = (String)subject.getPrincipal();
		 String username = JWTUtil.getUsername(token);
		 User user3= userService.selectByName(username);
		 String [] strings = id.split(",");
		 List<Integer> ids =  new ArrayList<>();
		 for (String string : strings) {
				Integer n = Integer.valueOf(string);
				ids.add(n);
			}
		 
		  for (Integer integer : ids) {
			  List<Integer> perIds = new ArrayList<>();
				List<PermissionDto> list = permissionService.selectSonPermissions(integer);
				for (PermissionDto permissionDto : list) {
					  perIds.add(permissionDto.getId());
				}
			//如果有子权限,并且父权限不为空时,先删除子权限
			if (list!=null && list.size()!=0 && permissionService.selectById(integer).getpId()!=null) {
				
				for (Integer integer2 : perIds) {
					int num = permissionService.deleteByPrimaryKey(integer2);
					if (num<=0) {
						throw new CustomException("删除子权限失败");
					}
					//删除子权限对应的角色权限表记录
					rolePermissionService.deleteByPerId(integer2);
				}
				 //删除权限
				 int num = permissionService.deleteByPrimaryKey(integer);
				 if (num<=0) {
					throw new CustomException("删除权限失败");
				}
				//删除权限对应的角色权限表记录
			   rolePermissionService.deleteByPerId(integer);
				
			}
		}
		      
		       
		  for (Integer integer : ids) {
			    System.out.println(integer+"-------");
			    List<Integer> perIds = new ArrayList<>();
				List<PermissionDto> list = permissionService.selectSonPermissions(integer);
				for (PermissionDto permissionDto : list) {
					  perIds.add(permissionDto.getId());
				}
				//如果有子权限,并且父权限为空时
				if (list!=null && list.size()!=0 && permissionService.selectById(integer).getpId()==null) {
					//判断子权限下有没有子权限
					for (Integer integer2 : perIds) {
						List<PermissionDto> lists = permissionService.selectSonPermissions(integer2);
						 List<Integer> pers = new ArrayList<>();
						if (lists!=null && lists.size()!=0) {
							for (PermissionDto permissionDto : lists) {
								  pers.add(permissionDto.getId());
							}
							//如果子权限下有子权限的话,先删除所有子权限
							for (Integer integer3 : pers) {
								int num1 = permissionService.deleteByPrimaryKey(integer3);
								if (num1<=0) {
									throw new CustomException("删除子权限的子权限失败!");
								}
								//删除所有子权限下的子权限对应的角色权限表记录
								rolePermissionService.deleteByPerId(integer3); 
							}
							//删除子权限
							int son = permissionService.deleteByPrimaryKey(integer2);
							if (son<=0) {
								 throw new CustomException("删除子权限失败!");
							}
						}else {
							//如果子权限下没有子权限的话,先删除子权限
							int son = permissionService.deleteByPrimaryKey(integer2);
							if (son<=0) {
								 throw new CustomException("删除子权限失败!");
							}
							//删除子权限对应的角色权限表中的记录
							rolePermissionService.deleteByPerId(integer2); 
						}
					} 
					int num = permissionService.deleteByPrimaryKey(integer);
					 if (num<=0) {
							throw new CustomException("删除权限失败");
						}
					   //删除权限对应的角色权限表记录
					   rolePermissionService.deleteByPerId(integer);
				}else if(list==null || list.size()==0){
					        permissionService.deleteByPrimaryKey(integer);
						   //删除权限对应的角色权限表记录
						   rolePermissionService.deleteByPerId(integer);
				  }
			}
		 
		    //插入后插入日志表中
			Logger loggers  = new Logger();
			SimpleDateFormat  sdf  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Timestamp timestamp = Timestamp.valueOf(sdf.format(new Date()));
			loggers.setActionname("删除");
			loggers.setHappendate(timestamp);
			loggers.setIp(request.getRemoteAddr());
			loggers.setUserid(user3.getId());
			int result = loggerService.insert(loggers);
			if (result<=0) {
				throw new CustomException("插入日志表失败");
			}
		  return new ResponseBean(true, 200,"[delete permissions successfully]", null);	
		 
	 }
	
	
	
	@RequestMapping("/getPermissions")
	public ResponseBean getList(){
		
		List<PermissionDto> permission = new TreeUtil<PermissionDto>().getChildTree(permissionService.selectAll());    
		return new ResponseBean(true, 200,"[you are daidaishou]", permission);
		
	}
	
	

	@RequiresAuthentication
	@RequestMapping("/getInfo")
	public ResponseBean getInfo(Integer id){
			 
		PermissionDto permission = permissionService.selectById(id);
		return new ResponseBean(true, 200,"[you are daidaishou]", permission);
		
	}
	
	@RequiresAuthentication
	@RequestMapping("/getSonPermissions")
	public ResponseBean getSonPermissions(Integer id){
			 
		
		List<PermissionDto> permissions = permissionService.selectSonPermissions(id);
		return new ResponseBean(true, 200,"[you are daidaishou]", permissions);
		
	}
	
	
}
