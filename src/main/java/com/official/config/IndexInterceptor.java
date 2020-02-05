package com.official.config;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.official.bean.Website;
import com.official.exception.CustomException;
import com.official.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class IndexInterceptor  implements HandlerInterceptor{
     @Autowired
     private WebsiteService websiteService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		 String ip =  request.getRemoteAddr();
		 System.out.println(ip);
		 if (ip!=null && ip!="") {
			    SimpleDateFormat  sdf  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Timestamp timestamp = Timestamp.valueOf(sdf.format(new Date()));
			    Integer visitCount = 1;
				Website website = new Website();
				website.setIp(ip);
				website.setVisitcount(visitCount);
				website.setVisitdate(timestamp);
				int n = websiteService.insert(website);
				if (n<=0) {
					throw new CustomException("插入统计表失败!");
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
