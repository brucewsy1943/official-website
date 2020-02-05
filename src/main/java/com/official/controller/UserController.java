package com.official.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.official.bean.ResponseBean;
import com.official.bean.dto.UserDto;
import com.official.exception.UnauthorizedException;
import com.official.service.LoggerService;
import com.official.service.UserService;
import com.official.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.official.bean.User;
import com.official.bean.User_Role;
import com.official.exception.CustomException;
import com.official.service.User_RoleService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private User_RoleService user_RoleService;
	
	@Autowired
	private LoggerService loggerService;
	
	@ApiOperation(value="新增用户",notes="新增用户")
	 @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
	 @RequiresPermissions("user:create")
	 @PostMapping("/create")
	 public ResponseBean add(@RequestBody User user, HttpServletRequest request){
		 Subject subject = SecurityUtils.getSubject();
		 String token = (String)subject.getPrincipal();
		 String username = JWTUtil.getUsername(token);
		 User user3= userService.selectByName(username);
		 String md5Pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		 user.setPassword(md5Pwd);
		 user.setCreatedTime(new Timestamp(new Date().getTime()));
		 if (user.getOrderId()==0) {
				user.setOrderId(99);
			}
		 int n = userService.insert(user);
		 if (n<=0) {
			throw new CustomException("新增用户失败");
		}
		 
		 User user2 = userService.selectByName(user.getLoginname());
		 User_Role user_Role = new User_Role();
		 user_Role.setRoleid(user2.getRoleId());
		 user_Role.setUserid(user2.getId());
		 int num = user_RoleService.insert(user_Role);
		 if (num<0) {
			 throw new CustomException("向用户角色表中插入数据失败!");
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
		 logger.info("新增用户成功!");
		 return new ResponseBean(true, 200, "[insert a user successfully]", null);
		 
	 }

	 @ApiOperation(value="用户登录",notes="用户登录")
	 @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
	 @PostMapping("/login")
	public ResponseBean login(@RequestBody UserDto user, HttpServletResponse response, HttpServletRequest request){
		System.out.println("登录"+request.getSession().getId());
		 User user2 = userService.selectByName(user.getLoginname());
		 //String md5Pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());//md5加密
		 String md5Pwd = user2.getPassword();
		 System.out.println(user2.getPassword());
			if (user2.getPassword().equals(md5Pwd)) {
				user2.setLastlogintime(new Timestamp(new Date().getTime()));
				int n = userService.updateByPrimaryKey(user2);
				if (n<0) {
					throw new CustomException("更新失败");
				}
				
				String token = JWTUtil.sign(user.getLoginname(),md5Pwd);
				response.setHeader("Authorization", token);
				response.setHeader("Access-Control-Expose-Headers", "Authorization");
				//登录后插入日志表中
				com.official.bean.Logger logger  = new com.official.bean.Logger();
				SimpleDateFormat  sdf  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Timestamp timestamp = Timestamp.valueOf(sdf.format(new Date()));
				logger.setActionname("登录");
				logger.setHappendate(timestamp);
				logger.setIp(request.getRemoteAddr());
				logger.setUserid(user2.getId());
				int result = loggerService.insert(logger);
				if (result<=0) {
					throw new CustomException("插入日志表失败");
				}
				return new ResponseBean(true,200,"[you were  logined successfully ]", user2);
			}else {
				throw new UnauthorizedException();
			}
		// return null;
	 }
	
	
	 @ApiOperation(value="更新用户",notes="更新用户")
	 @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
	 @RequiresPermissions("user:update")
	 @PostMapping("/update")
	 public ResponseBean update(@RequestBody User user,HttpServletRequest request){
		 Subject subject = SecurityUtils.getSubject();
		 String token = (String)subject.getPrincipal();
		 String username = JWTUtil.getUsername(token);
		 User user3= userService.selectByName(username);
		 User user2 = userService.selectByPrimaryKey(user.getId());
		 user.setCreatedTime(user2.getCreatedTime());
		 user.setModifiedTime(new Timestamp(new Date().getTime()));
		 user.setLastlogintime(user2.getLastlogintime());
		 if (user.getPassword().equals(user2.getPassword()) ) {
		}else {
			 String md5Pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		     user.setPassword(md5Pwd);
		}
		
		 if (user.getOrderId()==0) {
				user.setOrderId(99);
			}
		 int n = userService.updateByPrimaryKey(user);
		 
		 if (n<=0) {
			throw new CustomException("更新用户失败");
		}
		   //更新后插入日志表中
			com.official.bean.Logger loggers  = new com.official.bean.Logger();
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
		 logger.info("更新用户成功!");
		 if (user2.getId() == user3.getId()) {
			 return new ResponseBean(true, 200, "[update the user successfully]", 1);
		}else {
			return new ResponseBean(true, 200, "[update the user successfully]",0);
		}
		 
		 
	 }
	 
	 
	 @ApiOperation(value="删除用户",notes="根据id删除用户")
	 @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "java.lang.String")
	 @RequiresPermissions("user:delete")
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
	    	 int n = userService.deleteByPrimaryKey(integer);
				if (n<=0) {
					throw new CustomException("删除用户失败");
				}
			 int num = user_RoleService.deleteByUserId(integer);
			 if (num<=0) {
					throw new CustomException("删除用户下的角色失败");
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
			int result = loggerService.insert(loggers);
			if (result<=0) {
				throw new CustomException("插入日志表失败");
			}
			 logger.info("删除用户成功!");
			 return new ResponseBean(true, 200, "[delete the user successfully]", null);
		}
	 
	 @ApiOperation(value="获取所有用户信息",notes="获取所有用户信息")
	 @RequiresPermissions("user:findAll")
	 @PostMapping("/getUserList")
	public ResponseBean getUserList(){
		 List<UserDto> users = userService.selectAll();
		 
		return new ResponseBean(true, 200, "[query all users's information successfully]", users);
	 }
	 
	 @ApiOperation(value="查询用户信息",notes="根据id查询用户信息")
	 @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "java.lang.Integer")
	 @RequiresPermissions("user:findOne")
	 @PostMapping("/getInfo")
	 public ResponseBean getInfo(Integer id){
		 UserDto user = userService.selectByPrimaryKey(id);
		 if (user==null) {
			throw new CustomException("获取用户信息失败!");
		}
		 logger.info("获取用户信息成功!");
		return new ResponseBean(true, 200,"[query the user's information successfully]", user);
		 
	 }
	 
	 @ApiOperation(value="关键字查询用户信息",notes="根据关键字查询用户信息")
	 @ApiImplicitParam(name = "keyword", value = "关键字", required = true, dataType = "java.lang.String")
	 @PostMapping("/fuzzySearch")
	 public ResponseBean fuzzySearch(String keyword){
		List<UserDto> users = userService.fuzzySearch(keyword);
		 logger.info("获取用户信息成功!");
		return new ResponseBean(true, 200,"[query the user's information successfully]", users);
		 
	 }
	 
	 
	 @ApiOperation(value="查询登录名是否存在",notes="查询登录名是否存在")
	 @ApiImplicitParam(name = "loginname", value = "登录名", required = true, dataType = "java.lang.String")
	 @PostMapping("/checkName")
	 public ResponseBean checkName(String loginname){
		List<User> users = userService.checkName(loginname);
		 if (users.size()<1) {
			 return new ResponseBean(true, 200,"[query the user's information successfully]", 1);
		}else{
			return new ResponseBean(true, 200,"[query the user's information successfully]", 0);
		}
		
		 
	 }
	 
	 
	 
	 }

