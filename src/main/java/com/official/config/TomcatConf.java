package com.official.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

@Configuration
public class TomcatConf {
	
	   @Value("${spring.server.MaxFileSize}")
	    private String MaxFileSize;
	    @Value("${spring.server.MaxRequestSize}")
	    private String MaxRequestSize;

	    @Bean
	    public MultipartConfigElement multipartConfigElement() {
	        MultipartConfigFactory factory = new MultipartConfigFactory();
	        //  单个数据大小
	        //factory.setMaxFileSize(MaxFileSize); // KB,MB
	        factory.setMaxFileSize(DataSize.of(50L, DataUnit.MEGABYTES));
	        /// 总上传数据大小
	        //factory.setMaxRequestSize(MaxRequestSize);
	        factory.setMaxRequestSize(DataSize.of(500L, DataUnit.MEGABYTES));
	        return factory.createMultipartConfig();
	    }

}
