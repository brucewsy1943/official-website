package com.official.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {

	
	@Bean
    public HandlerInterceptor getMyInterceptor(){
        return new ContentInterceptor();
    }
	
	@Bean
    public HandlerInterceptor getInterceptor(){
        return new IndexInterceptor();
    }

	@Bean
	public HandlerInterceptor getIPInterceptor(){
		return new IPcountInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/*registry.addInterceptor(getMyInterceptor())
		              .addPathPatterns("/article/getInformation");*/
		/*registry.addInterceptor(getInterceptor())
		              .addPathPatterns("/article/index");*/
		registry.addInterceptor(getIPInterceptor());
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		WebMvcConfigurer.super.addViewControllers(registry);
	}

	
	
}
