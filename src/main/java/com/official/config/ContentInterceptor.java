package com.official.config;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.official.bean.Article;
import com.official.service.ArticleService;
import com.official.service.WebsiteContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.official.bean.WebsiteContent;
import com.official.exception.CustomException;

public class ContentInterceptor  implements HandlerInterceptor{
    @Autowired
    private ArticleService articleService;
   
    
    @Autowired
    private WebsiteContentService websiteContentService;
    
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 String id = request.getParameter("id"); 
		 String ip = request.getRemoteAddr();
		 if (id!=null && id!="" &&ip!=null &&ip!="") {
			 Article article = articleService.selectByPrimaryKey(Integer.valueOf(id));
			 SimpleDateFormat  sdf  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 Timestamp timestamp = Timestamp.valueOf(sdf.format(new Date()));
			 WebsiteContent websiteContent = new WebsiteContent();
			 websiteContent.setArticleId(Integer.valueOf(id));
			 websiteContent.setIp(ip);
			 websiteContent.setVisitcount(1);
			 websiteContent.setColumnid(article.getColumnid());
			 websiteContent.setVisitdate(timestamp);
		     int num = websiteContentService.insert(websiteContent);
		     if (num<=0) {
				throw new CustomException("插入网站内容分析失败");
			}
		     article.setClicks(article.getClicks()+1);
		     article.setModifiedTime(timestamp);
		     int result = articleService.updateByPrimaryKey(article);
		     if (result<=0) {
		    	 throw new CustomException("修改文章点击量失败");
			}
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	

}
