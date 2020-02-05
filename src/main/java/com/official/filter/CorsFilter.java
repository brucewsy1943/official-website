package com.official.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
/*
 * 跨域过滤器
 */
@Configuration
public class CorsFilter implements Filter {
    public CorsFilter() {
   
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	   HttpServletResponse res = (HttpServletResponse) response;  
	   HttpServletRequest req = (HttpServletRequest)request;
	    res.setHeader("Access-Control-Allow-Origin",req.getHeader("Origin")); 
	    res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, GET");  
	    res.setHeader("Access-Control-Max-Age", "3600");  
	    res.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");  
	    res.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}
    
	public void destroy() {
		
	}
}
