package com.official.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.official.bean.ResponseBean;
import com.official.bean.dto.FrontTreeNode;
import com.official.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.official.bean.Column;
import com.official.bean.User;
import com.official.bean.dto.ColumnDto;
import com.official.exception.CustomException;
import com.official.service.ColumnService;
import com.official.service.LoggerService;
import com.official.service.UserService;
import com.official.tree.TreeUtils;
import com.official.utils.UploadUtils;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/column")
public class ColumnController {
      private Logger logger = LoggerFactory.getLogger(ColumnController.class);
	
	 @Autowired
	 private ColumnService columnService;

	 @Autowired
	 private UploadUtils UploadUtils;
	 
	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 private LoggerService loggerService;
	 /**
	  * 上传栏目预览图至tomcat服务器中存储
	  * @param file
	  * @return
	  */
	 @ApiOperation(value="上传栏目预览图",notes="栏目预览图存储到tomcat服务器")
	 @ApiImplicitParam(name = "file", value = "文件预览图", required = true, dataType = "org.springframework.web.multipart.MultipartFile")
	 @PostMapping("/upload")
	 public ResponseBean uploadFile(@RequestParam("file")MultipartFile file) {
		 String columnPreview = UploadUtils.uploadColumnPreview(file,"/img/");
		 logger.info("-----"+columnPreview);
		return new ResponseBean(true, 200, "[upload the picture successfully]", columnPreview);
		
	}

	 @ApiOperation(value="新增栏目",notes="新增栏目")
	 @ApiImplicitParam(name = "column", value = "栏目实体", required = true, dataType = "Column")
	 @RequiresPermissions("column:create")
	 @PostMapping("/create")
	 public ResponseBean add(@RequestBody Column column,HttpServletRequest request){
		 Subject subject = SecurityUtils.getSubject();
		 String token = (String)subject.getPrincipal();
		 String username = JWTUtil.getUsername(token);
		 User user3= userService.selectByName(username);
		 int n = columnService.insert(column);
		 if (n<=0) {
			throw new CustomException("新增栏目失败");
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
		 logger.info("新增栏目成功!");
		 return new ResponseBean(true, 200, "[insert a column successfully]", null);
		 
	 }

	 @ApiOperation(value="更新栏目",notes="更新栏目")
	 @ApiImplicitParam(name = "column", value = "栏目实体", required = true, dataType = "Column")
	 @RequiresPermissions("column:update")
	 @PostMapping("/update")
	 public ResponseBean update(@RequestBody Column column,HttpServletRequest request){
		 Subject subject = SecurityUtils.getSubject();
		 String token = (String)subject.getPrincipal();
		 String username = JWTUtil.getUsername(token);
		 User user3= userService.selectByName(username);
		 int n = columnService.update(column);
		 if (n<=0) {
			throw new CustomException("更新栏目失败");
		}
		   //插入后插入日志表中
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
		 logger.info("更新栏目成功!");
		 return new ResponseBean(true, 200, "[update the column successfully]", null);
		 
	 }
	 
	 @ApiOperation(value="删除栏目",notes="根据id删除栏目")
	 @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "java.lang.Integer")
	 @RequiresPermissions("column:delete")
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
			 List<Integer> integers =  columnService.selectSonId(integer);
			 if (ids.size()==0||ids==null) {
				int n = columnService.deleteById(integer);
				if (n<=0) {
					throw new CustomException("删除栏目失败");
				}
			}else {
				for (Integer n : integers) {
					 columnService.deleteById(n);
				}
				columnService.deleteById(integer);	
			}  
		}   
		    //插入后插入日志表中
			com.official.bean.Logger loggers  = new com.official.bean.Logger();
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
		    logger.info("删除栏目成功!");  
			return new ResponseBean(true, 200, "[delete the column successfully]", null);
		 
	 }
	 
	 @ApiOperation(value="获取所有栏目信息",notes="获取所有栏目信息")
	 @PostMapping("/getColumnList")
      	public ResponseBean getColumnList(){
		 List<Map<String, Object>> mapList = new ArrayList<>();
		Map<String, Object> orgMap = new HashMap<String, Object>();
		orgMap.put("isParent", true);//设置根节点为父节点
		orgMap.put("open", true); //根节点展开
		orgMap.put("id", 0);//根节点的ID
		orgMap.put("name", "根节点"); //根节点的名字
		mapList.add(orgMap); //列表中先添加第一级的数据
		
		//添加第二级、第三级的数据
		List<Column> columns = columnService.getColumnsInfo();
		//把所有第二级的id保存到一个list中
		for (Column column : columns) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", column.getId());
			map.put("pid", column.getParent_id());
			map.put("name", column.getColumnName());
			map.put("columnPreview", column.getColumnPreview());
			map.put("isHidden", column.getIsHidden());
			map.put("linkType", column.getLinkType());
			map.put("linkUrl", column.getLinkUrl());
			map.put("refNo", column.getRefNo());
			map.put("remark", column.getRemark());
			map.put("template", column.getTemplate());
			map.put("addContent", column.getAddContent());
			map.put("open", true);
			mapList.add(map);
		}
		
		 if (columns.size()==0||columns==null) {
			 logger.info("获取栏目信息失败");
			throw  new CustomException("获取所有栏目信息失败");
		}
		 
		return new ResponseBean(true, 200,"[query all columns's info successfully]", mapList);
		 
	 }
	 
	 
	 
	 
	 @ApiOperation(value="获取所有栏目信息",notes="获取所有栏目信息")
	 @PostMapping("/getColumns")
      	public ResponseBean getColumns(){
		List<Column> columns = columnService.getColumnsInfo();
		List<Column> columnlist = new TreeUtils<Column>().getChildTree(columns) ;
		return new ResponseBean(true, 200,"[query all columns's info successfully]", columnlist);
		 
	 }
	 
	 @ApiOperation(value="查询栏目信息",notes="根据id查询栏目信息")
	 @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "java.lang.Integer")
	 @PostMapping("/getInfo")
	 public ResponseBean getInfo(Integer id){
	
		 Column column = columnService.selectById(id);
		 if (column==null) {
			throw new CustomException("获取栏目信息失败!");
		}else {
				    logger.info("获取栏目信息成功!");
					return new ResponseBean(true, 200,"[query the column's information successfully]", column);
		}	 
	 }
	 
	 
	 
	 @ApiOperation(value="查询栏目信息",notes="根据id查询栏目信息")
	 @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "java.lang.Integer")
	 @PostMapping("/getSons")
	 public ResponseBean getSons(Integer id){
		 List<Column> columns = columnService.findSonColumn(id);
		 if (id==0) {
			   logger.info("获取栏目信息成功!");
			   return new ResponseBean(true, 200,"[query the column's information successfully]", columns);
		}
		 Column column = columnService.selectById(id);
		 if (column==null) {
			throw new CustomException("获取栏目信息失败!");
		}else {
			if (columns.size()==0||columns==null) {
				   logger.info("获取栏目信息成功!");
					return new ResponseBean(true, 200,"single", column);
			}else {
				    logger.info("获取栏目信息成功!");
					return new ResponseBean(true, 200,"[query the column's information successfully]", columns);
			}
		}
	 }

	/**
	 * 获取所有栏目
	 * @return
     */
	@ApiOperation(value="查询整棵树",notes="查询整棵树")
	@ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "java.lang.Integer")
	@PostMapping("/getTree")
	public ResponseBean getTree(){
		List<Column> columns = columnService.getColumnsInfo();
		List<FrontTreeNode> list = transferToFrontTreeNodes(columns);
		List<FrontTreeNode> columnlist = new TreeUtils<FrontTreeNode>().getChildTree(list);
		List<FrontTreeNode> result = generateBanner(columnlist);
		return new ResponseBean(true, 200,"[query all columns's info successfully]", result);
	}
	 
	 
	 @ApiOperation(value="关键字查询栏目信息",notes="根据栏目名称和父栏目id查询节目")
	 @ApiImplicitParams({
		 @ApiImplicitParam(name = "parent_id", value = "父栏目id", required = true, dataType = "java.lang.Integer"),
		 @ApiImplicitParam(name = "columnName", value = "栏目名", required = true, dataType = "java.lang.String")
	 })
	 @PostMapping("/fuzzySearch")
	 public ResponseBean fuzzySearch(String keyword){
		/* if (StringUtil.isBlank(keyword)) {
			 throw  new CustomException("栏目名为空,查询失败!");
		 }*/
		 List<Column> columns = columnService.fuzzySearch(keyword);
         logger.info("模糊查询成功!");
		return new ResponseBean(true, 200,"[ the fuzzySearching was successful]",columns );
		 
	 }
	 
	 
	 
	 @ApiOperation(value="查询栏目信息",notes="根据id查询栏目信息")
	 @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "java.lang.Integer")
	 @PostMapping("/getParent")
	 public ResponseBean getParent(Integer id){
			int parentId = columnService.selectParentId(id);
			logger.info("获取栏目信息成功!");
			return new ResponseBean(true, 200,"[query the column's information successfully]", parentId);
	 }


	private List<FrontTreeNode> transferToFrontTreeNodes(List<Column> columns){
		List<FrontTreeNode> frontTreeNodes = new ArrayList<>();
		for (Column column:columns) {
			System.out.print(column);
			FrontTreeNode ftn = new FrontTreeNode();
			ftn.setHref(column.getTemplate());
			ftn.setText(column.getColumnName());
			ftn.setTags(null);
			ftn.setId(column.getId());
			ftn.setParentId(column.getParent_id());
			ftn.setLinkType(column.getLinkType());
			ftn.setRefno(column.getRefNo());
			ftn.setBannerPic(column.getColumnPreview());
			ftn.setRemark(column.getRemark());
			frontTreeNodes.add(ftn);

		}
		return frontTreeNodes;
	}

	//处理一下banner图。因为只有一级栏目有，所以把一级所有下面的栏目都手动加上banner
	private List<FrontTreeNode> generateBanner(List<FrontTreeNode> firstColumns){
		for (int i = 0; i < firstColumns.size(); i++) {
			FrontTreeNode firstColumn = firstColumns.get(i);
			List<FrontTreeNode> children = firstColumn.getNodes();
			//对子栏目进行递归查找
			if (children!=null && children.size()>0){
				recursion(children,firstColumn.getBannerPic());
			}
		}

		return firstColumns;
	}

	//递归查找方法
	private void recursion(List<FrontTreeNode> children,String bannerPic){
		for (int j = 0; j < children.size(); j++) {
			FrontTreeNode childrenColumn = children.get(j);
			if (childrenColumn.getBannerPic() == null || "".equals(childrenColumn.getBannerPic())){
				childrenColumn.setBannerPic(bannerPic);
			}
			if (childrenColumn.getNodes() != null && childrenColumn.getNodes().size() > 0) {
				recursion(childrenColumn.getNodes(), childrenColumn.getBannerPic());
			}
		}
	}

}
