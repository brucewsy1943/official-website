package com.official.controller;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.official.bean.Article;
import com.official.bean.ResponseBean;
import com.official.bean.dto.ArticleDto;
import com.official.service.ArticleService;
import com.official.service.ColumnService;
import com.official.service.LoggerService;
import com.official.service.UserService;
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

import com.github.pagehelper.PageInfo;
import com.official.bean.User;
import com.official.bean.dto.PageDto;
import com.official.exception.CustomException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/article")
public class ArticleController {
	
	private Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ColumnService columnService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	 private com.official.utils.UploadUtils UploadUtils;
	
	@Autowired
	private LoggerService loggerService;
	 /**
	  * 上传栏目预览图至tomcat服务器中存储
	  * @param file
	  * @return
	  */ 
	 /*@ApiOperation(value="上传文章导航图",notes="文章导航图存储到tomcat服务器")
	 @ApiImplicitParam(name = "file", value = "文章导航图", required = true, dataType = "org.springframework.web.multipart.MultipartFile")
	 @PostMapping("/upload")
	 public ResponseBean uploadFile(String file) {
		 //时间戳避免文件名重复
		 String navPreview= UploadUtils.GenerateImage(file, "/img/"+System.currentTimeMillis()+".jpg");
		 logger.info("-----"+navPreview);
		 return new ResponseBean(true, 200, "[upload the picture successfully]", navPreview);
		
	}*/

	@ApiOperation(value="上传栏目预览图",notes="栏目预览图存储到tomcat服务器")
	@ApiImplicitParam(name = "file", value = "文件预览图", required = true, dataType = "org.springframework.web.multipart.MultipartFile")
	@PostMapping("/uploadnav")
	public ResponseBean uploadNavPic(@RequestParam("file")MultipartFile file) {
		String columnPreview = UploadUtils.uploadColumnPreview(file,"/img/");
		logger.info("-----"+columnPreview);
		return new ResponseBean(true, 200, "[upload the picture successfully]", columnPreview);

	}
	 
	 /**
	  * 上传栏目预览图至tomcat服务器中存储
	  * @param file
	  * @return
	  */
	 @ApiOperation(value="上传附件",notes="上传附件")
	 @ApiImplicitParam(name = "file", value = "附件", required = true, dataType = "org.springframework.web.multipart.MultipartFile")
	 @PostMapping("/uploadfile")
	 public ResponseBean uploadFile(@RequestParam("file")MultipartFile file) {
		 String columnPreview = UploadUtils.uploadColumnPreview(file,"file/");//这个文件夹一定要存在才能成功！
		 logger.info("-----"+columnPreview);
		return new ResponseBean(true, 200, "[upload the picture successfully]", columnPreview);
	 }	
	 
	 
	 @ApiOperation(value="新增文章",notes="新增文章")
	 @ApiImplicitParam(name = "article", value = "文章实体", required = true, dataType = "ArticleDto")
	 @RequiresPermissions("article:create")
	 @PostMapping("/create")
	 public ResponseBean add(@RequestBody ArticleDto article, HttpServletRequest request) throws Exception{
		 Subject subject = SecurityUtils.getSubject();
		  String username = JWTUtil.getUsername((String)subject.getPrincipal());
		  User user = userService.selectByName(username);
		  Article article2 = new Article();
		  String columnIds = article.getColumnIds();
		  String [] ids = columnIds.split(",");
		  List<Integer> integers = new ArrayList<>();
		
		for (String string : ids) {
		  Integer integer = Integer.valueOf(string);
		  integers.add(integer);
		}
     
		if (article.getPubtimetype()==0) {			
			article2.setPubtime(new Timestamp(new Date().getTime()));
		}else {
			if (article.getPubtimes() == null || article.getPubtimes()=="") {
				article2.setPubtime(new Timestamp(new Date().getTime()));
			}else {
				article2.setPubtime(Timestamp.valueOf(article.getPubtimes()));
			}
		}

		if (article.getExpiredtimetype()==0) {
			article2.setExpiredtime(Timestamp.valueOf("2099-09-01 00:00:00"));
		}else {
			if (article.getExpiredtimes() == null || article.getExpiredtimes()=="") {
				article2.setExpiredtime(Timestamp.valueOf("2099-09-01 00:00:00"));
			}else {
				article2.setExpiredtime(Timestamp.valueOf(article.getExpiredtimes()));
			}
		}
		  article2.setPubtimetype(article.getPubtimetype());
		  article2.setExpiredtimetype(article.getExpiredtimetype());
		  article2.setAttachmentpath(article.getAttachmentpath());
		  article2.setClicks(article.getClicks());
		  article2.setContent(article.getContent());
		  article2.setContentsource(article.getContentsource());
		  article2.setInterception(article.getInterception());
		  article2.setIsstick(article.getIsstick());  
		  if (article.getIsstick()==1) {
			  article2.setOrderpriority(1);
		}else{
			 if (article.getOrderpriority() == 0) {
				 article2.setOrderpriority(99);
			 }else{
				 article2.setOrderpriority(article.getOrderpriority());
			 }
			 
		}
		  article2.setLinktype(article.getLinktype());
		  article2.setLinkurl(article.getLinkurl());
		  article2.setNavpicturepath(article.getNavpicturepath());
		 
		  article2.setStatus(article.getStatus());
		  article2.setTitle(article.getTitle());
		  article2.setUserId(user.getId());
          article2.setCreatedTime(new Timestamp(new Date().getTime()));
		 article2.setSubTitle(article.getSubTitle());
		  for (Integer integer : integers) {
			article2.setColumnid(integer);
			 int n = articleService.insert(article2);
			 if (n<=0) {
				throw new CustomException("新增文章失败");
			}
			
		} 
		   //插入后插入日志表中
			com.official.bean.Logger loggers  = new com.official.bean.Logger();
			SimpleDateFormat  sdf  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Timestamp timestamp = Timestamp.valueOf(sdf.format(new Date()));
			loggers.setActionname("插入");
			loggers.setHappendate(timestamp);
			loggers.setIp(request.getRemoteAddr());
			loggers.setUserid(user.getId());
			int result = loggerService.insert(loggers);
			if (result<=0) {
				throw new CustomException("插入日志表失败");
			}
		   logger.info("新增文章成功!");
		   
		   return new ResponseBean(true, 200, "[insert an article successfully]", null);
		 
	 }

	 @ApiOperation(value="更新文章",notes="更新文章")
	 @ApiImplicitParam(name = "article", value = "文章实体", required = true, dataType = "Article")
	 @RequiresPermissions("article:update")
	 @PostMapping("/update")
	 public ResponseBean update(@RequestBody ArticleDto article,HttpServletRequest request) throws Exception{
		 System.out.println(article);
		    Subject subject = SecurityUtils.getSubject();
		    String username = JWTUtil.getUsername((String)subject.getPrincipal());
		    User user = userService.selectByName(username);
		    Article article2 = new Article();
			String columnIds = article.getColumnIds();
			String [] ids = columnIds.split(",");
			List<Integer> integers = new ArrayList<>();		
			for (String string : ids) {
			  Integer integer = Integer.valueOf(string);
			  integers.add(integer);
			}
            
			  if (integers.size()>1 || integers.size()==0) {
				  throw new CustomException("栏目不许超过一个!");
			}
			
				article2.setPubtime(Timestamp.valueOf(article.getPubtimes()));
			if (article.getExpiredtimetype()==0) {
				article2.setExpiredtime(Timestamp.valueOf("2099-09-01 00:00:00"));
			}else {
				article2.setExpiredtime(Timestamp.valueOf(article.getExpiredtimes()));
			}
			  article2.setPubtimetype(article.getPubtimetype());
			  article2.setExpiredtimetype(article.getExpiredtimetype());
			  article2.setAttachmentpath(article.getAttachmentpath());
			  article2.setClicks(article.getClicks());
			  article2.setContent(article.getContent());
			  article2.setContentsource(article.getContentsource());
			  article2.setInterception(article.getInterception());
			  article2.setIsstick(article.getIsstick());
			  if (article.getIsstick()==1) {
				  article2.setOrderpriority(1);
			}else{
				// if (article.getOrderpriority()==1) {
					 /*article2.setOrderpriority(99);*/
				//}else {
					article2.setOrderpriority(article.getOrderpriority());
			//	}
				  
			}
			  article2.setLinktype(article.getLinktype());
			  article2.setLinkurl(article.getLinkurl());
			  article2.setNavpicturepath(article.getNavpicturepath());
			  article2.setStatus(article.getStatus());
			  article2.setTitle(article.getTitle());
			  article2.setUserId(user.getId());
			  article2.setId(article.getId());
	          article2.setColumnid(integers.get(0));
	          article2.setCreatedTime(Timestamp.valueOf(article.getCreatedtime()));
	          article2.setModifiedTime(new Timestamp(new Date().getTime()));
			 article2.setSubTitle(article.getSubTitle());
		     int n = articleService.updateByPrimaryKey(article2);
		 if (n<=0) {
			throw new CustomException("更新文章失败");
		}
		   //插入后插入日志表中
			com.official.bean.Logger loggers  = new com.official.bean.Logger();
			SimpleDateFormat  sdf  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Timestamp timestamp = Timestamp.valueOf(sdf.format(new Date()));
			loggers.setActionname("更新");
			loggers.setHappendate(timestamp);
			loggers.setIp(request.getRemoteAddr());
			loggers.setUserid(user.getId());
			int result = loggerService.insert(loggers);
			if (result<=0) {
				throw new CustomException("插入日志表失败");
			} 
		 logger.info("更新文章成功!");
		 return new ResponseBean(true, 200, "[update the article successfully]", null);
		 
	 }
	 
	 
	 @ApiOperation(value="删除文章",notes="根据id删除文章")
	 @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "java.lang.Integer")
	 @RequiresPermissions("article:delete")
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
		    	 int n = articleService.deleteByPrimaryKey(integer);
					if (n<=0) {
						throw new CustomException("删除文章失败");
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
			 logger.info("删除文章成功!");
			 return new ResponseBean(true, 200, "[delete the article successfully]", null);
		}
	 
	 
	 @ApiOperation(value="获取所有文章信息",notes="获取所有文章信息")
	 @PostMapping("/getArticleList")
	public ResponseBean getArticleList(@RequestBody PageDto pageDto){
		PageInfo<ArticleDto> articles = articleService.selectAll(pageDto);
		 if (articles.getSize()==0||articles==null) {
			throw new CustomException("获取文章列表失败");
		}
		 
		return new ResponseBean(true, 200, "[query all articles's information successfully]", articles);
	 }
	 
	 
	 @ApiOperation(value="获取所有文章信息",notes="获取所有文章信息")
	 @PostMapping("/getArticleListScience")
	public ResponseBean getArticleListScience(@RequestBody PageDto pageDto){
		PageInfo<ArticleDto> articles = articleService.getArticleListScience(pageDto);
		 if (articles.getSize()==0||articles==null) {
			throw new CustomException("获取文章列表失败");
		}
		 
		return new ResponseBean(true, 200, "[query all articles's information successfully]", articles);
	 }
	 
	 
	 @ApiOperation(value="查询文章信息",notes="根据id查询文章信息")
	 @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "java.lang.Integer")
	 @PostMapping("/getInfo")
	 public ResponseBean getInfo(Integer id){
		 Article article = articleService.selectByPrimaryKey(id);
		 if (article==null) {
			throw new CustomException("获取文章信息失败!");
		}
		 logger.info("获取文章信息成功!");
		return new ResponseBean(true, 200,"[query the article's information successfully]", article);
		
	 }
	 
	 
	 @ApiOperation(value="查询文章信息",notes="根据id查询文章信息")
	 @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "java.lang.Integer")
	 @PostMapping("/getInformation")
	 public ResponseBean getInformation(Integer id){
		 Article article = articleService.selectByPrimaryKey(id);
		 if (article==null) {
			throw new CustomException("获取文章信息失败!");
		}
		 logger.info("获取文章信息成功!");
		return new ResponseBean(true, 200,"[query the article's information successfully]", article);
		 
	 }
	 
	 @ApiOperation(value="关键字查询文章信息",notes="根据发布时间和关键字查询文章信息")
	 @ApiImplicitParams({
		 @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, dataType = "java.lang.String"),
		 @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, dataType = "java.lang.String"),
		 @ApiImplicitParam(name = "keyword", value = "关键字", required = false, dataType = "java.lang.String")
	 })
	 @PostMapping("/fuzzySearch")
     public ResponseBean fuzzySearch(String startTime,String endTime,String keyword,Integer pageNum,Integer pageSize,Integer status){
		 if(startTime == null){
			 startTime = "";
		 }
		 if(endTime == null){
			 endTime = "";
		 }
		 if(keyword == null){
			 keyword = "";
		 }
		 PageDto pageDto = new PageDto();
		 pageDto.setPageNum(pageNum);
		 pageDto.setPageSize(pageSize);
		 PageInfo<ArticleDto> pageInfo = articleService.fuzzySearch(startTime, endTime, keyword, pageDto,status);
		return new ResponseBean(true, 200,"[fuzzySearch articles was successful]", pageInfo);
		 
	 }
	 
	 
	 @ApiOperation(value="栏目分类查询文章信息",notes="根据栏目id查询文章信息")
	 @ApiImplicitParams({
		 @ApiImplicitParam(name = "parentId", value = "父栏目id", required = false, dataType = "java.lang.Integer"),
		 @ApiImplicitParam(name = "id", value = "结束时间", required = false, dataType = "java.lang.Integer"),
		 @ApiImplicitParam(name = "pageNum", value = "父栏目id", required = false, dataType = "java.lang.Integer"),
		 @ApiImplicitParam(name = "pageSize", value = "结束时间", required = false, dataType = "java.lang.Integer"),
	 })
	 @PostMapping("/selectByParentId")
     public ResponseBean selectByParentId(Integer parentId,Integer id,Integer pageNum,Integer pageSize){
		 PageDto pageDto = new PageDto();
		 pageDto.setPageNum(pageNum);
		 pageDto.setPageSize(pageSize);
		 Integer count =0;
		 System.out.println(parentId+"---"+id);
		  if(id==0){
			   //当id为0时,则默认加载所有文章
			   PageInfo<ArticleDto> articleDtos = articleService.selectByParentId(parentId, id,pageDto);
			   return new ResponseBean(true, 200,"[query the articles successsfully]",articleDtos);
		   }
		 //当父栏目id为0时,需要判断是否有子栏目
			List<Integer> columns = columnService.selectSonId(id);
			if (columns.size()!=0) {
				  //当父栏目不为0且有子栏目时加载所有子栏目下的文章
				   if (parentId!=0 && id!=0) {
					   PageInfo<ArticleDto> articleDtos = articleService.selectByParentId(0, id,pageDto);
					   return new ResponseBean(true, 200,"[query the articles successsfully]",articleDtos);
				 }
				   
				   for (Integer integer : columns) {
					   List<Integer> ids = columnService.selectSonId(integer);
					   if (ids.size()>0) {
						    count++;
					}
				}
				   
				 
				   
				  if (count>0) {
					   //如果id不为0且父栏目id为0时,并且子栏目下还有子栏目时,加载所有子栏目下子栏目的文章
					   PageInfo<ArticleDto> articleDtos = articleService.selectByParentId(1, id,pageDto);
					   return new ResponseBean(true, 200,"[query the articles successsfully]",articleDtos);
				}else {
				    	//如果id不为0且父栏目id为0时,并且子栏目下没有子栏目时,加载所有子栏目下的文章
					   PageInfo<ArticleDto> articleDtos = articleService.selectByParentId(0, id,pageDto);
					   return new ResponseBean(true, 200,"[query the articles successsfully]",articleDtos);
				}
				      
			}else {
				//如果没有子栏目,则默认查询该栏目下的文章
				PageInfo<ArticleDto> articleDtos = articleService.selectByParentId(2,id ,pageDto);
				return new ResponseBean(true, 200,"[query the articles successsfully]",articleDtos);
			}
		 
	 }
	 
	 
	 
	 
	 @ApiOperation(value="栏目分类查询文章信息",notes="根据栏目id查询文章信息")
	 @ApiImplicitParams({
		 @ApiImplicitParam(name = "parentId", value = "父栏目id", required = false, dataType = "java.lang.Integer"),
		 @ApiImplicitParam(name = "id", value = "结束时间", required = false, dataType = "java.lang.Integer"),
		 @ApiImplicitParam(name = "pageNum", value = "父栏目id", required = false, dataType = "java.lang.Integer"),
		 @ApiImplicitParam(name = "pageSize", value = "结束时间", required = false, dataType = "java.lang.Integer"),
	 })
	 @PostMapping("/selectByParentIdScience")
     public ResponseBean selectByParentIdScience(Integer parentId,Integer id,Integer pageNum,Integer pageSize){
		 PageDto pageDto = new PageDto();
		 pageDto.setPageNum(pageNum);
		 pageDto.setPageSize(pageSize);
		 Integer count =0;
		 System.out.println(parentId+"---"+id);
		  if(id==0){
			   //当id为0时,则默认加载所有文章
			   PageInfo<ArticleDto> articleDtos = articleService.selectByParentIdScience(parentId, id,pageDto);
			   return new ResponseBean(true, 200,"[query the articles successsfully]",articleDtos);
		   }
		 //当父栏目id为0时,需要判断是否有子栏目
			List<Integer> columns = columnService.selectSonId(id);
			if (columns.size()!=0) {
				   for (Integer integer : columns) {
					   List<Integer> ids = columnService.selectSonId(integer);
					   if (ids.size()>0) {
						    count++;
					}
				}

				  if (count>0) {
					   //如果id不为0且父栏目id为0时,并且子栏目下还有子栏目时,加载所有子栏目下子栏目的文章
					   PageInfo<ArticleDto> articleDtos = articleService.selectByParentIdScience(1, id,pageDto);
					   return new ResponseBean(true, 200,"[query the articles successsfully]",articleDtos);
				}else {
				    	//如果id不为0且父栏目id为0时,并且子栏目下没有子栏目时,加载所有子栏目下的文章
					   PageInfo<ArticleDto> articleDtos = articleService.selectByParentIdScience(0, id,pageDto);
					   return new ResponseBean(true, 200,"[query the articles successsfully]",articleDtos);
				}
				      
			}else {
				//如果没有子栏目,则默认查询该栏目下的文章
				PageInfo<ArticleDto> articleDtos = articleService.selectByParentIdScience(2,id ,pageDto);
				return new ResponseBean(true, 200,"[query the articles successsfully]",articleDtos);
			}
		 
	 }
	 
	 @ApiOperation(value="栏目分类查询文章信息",notes="根据栏目id查询文章信息")
	 @ApiImplicitParams({
		 @ApiImplicitParam(name = "parentId", value = "父栏目id", required = false, dataType = "java.lang.Integer"),
		 @ApiImplicitParam(name = "id", value = "结束时间", required = false, dataType = "java.lang.Integer"),
		 @ApiImplicitParam(name = "pageNum", value = "父栏目id", required = false, dataType = "java.lang.Integer"),
		 @ApiImplicitParam(name = "pageSize", value = "结束时间", required = false, dataType = "java.lang.Integer"),
	 })
	 @PostMapping("/selectArticleSlide")
     public ResponseBean selectArticleSlide(Integer id){
		List<ArticleDto> articleDtos = articleService.selectArticleSlide(id);
		return new ResponseBean(true, 200,"[query four lastest articles successfully ]", articleDtos);
		 
	 }
	 
	 
	 @ApiOperation(value="文章置顶",notes="根据栏目id置顶文章")
	 @ApiImplicitParams({
		 @ApiImplicitParam(name = "orderId", value = "排序值", required = true, dataType = "java.lang.Integer"),
		 @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "java.lang.Integer"),
		 @ApiImplicitParam(name = "stickType", value = "置顶类型", required = true, dataType = "java.lang.Integer"),
	 })
	 @PostMapping("/articleStick")
     public ResponseBean articleStick(Integer id, Integer orderId,Integer stickType){
		 logger.info(id+"---"+orderId);
		 Article article = articleService.selectByPrimaryKey(id);
		 article.setOrderpriority(orderId);
		 article.setIsstick(stickType);
		 int n = articleService.articleStick(article);
		 if (n<=0) {
			throw new CustomException("文章置顶失败!");
		}
		 logger.info("文章置顶成功");
		return  new ResponseBean(true, 200,"[stick the article successfully]", null);
 
	 }
	 
	 
	 @ApiOperation(value="文章置顶",notes="根据栏目id置顶文章")
	 @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "java.lang.Integer")
	 @PostMapping("/previewPubnish")
     public ResponseBean previewPubnish(Integer id,Integer status){
		 Article article = articleService.selectByPrimaryKey(id);
		 if (status == 0) {
			 article.setStatus(1);
		}else if (status == 1) {
			 article.setStatus(0);
		}
		 
		 int n = articleService.previewPubnish(article);
		 if (n<=0) {
			throw new CustomException("文章预览发布失败!");
		}
		 logger.info("文章预览发布成功");
		return  new ResponseBean(true, 200,"[stick the article successfully]", null);
 
	 }

	@PostMapping("/getByColumn")
	public ResponseBean searchArticleByColumn(Integer columnId,Integer pageNum,Integer pageSize) throws Exception {
		if (columnId == null){
			throw new Exception("栏目id不能为空");
		}
		PageDto pageDto = new PageDto();
		pageDto.setPageNum(pageNum);
		pageDto.setPageSize(pageSize);
		PageInfo<ArticleDto> pageInfo = articleService.getByColumn(columnId,pageDto);
		return new ResponseBean(true, 200,"[fuzzySearch articles was successful]", pageInfo);
	}

	 
	 @ApiOperation(value="访问首页",notes="访问首页")
	 @PostMapping("/index")
     public ResponseBean index(){
		
		return  new ResponseBean(true, 200,"[stick the article successfully]", null);
	 } 
	 
	 
}
