/*package com.official.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

*//**
 * Created by Administrator on 2020/4/9.
 * 主要用于添加ueditor上传的图片的映射路径
 *//*
@Configuration
public class StaticResourceConf extends WebMvcConfigurerAdapter {

    @Value("${ueditor.dir}")
    private String ueditorUploadFilePath ;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        //配置访问前缀+实际资源所在位置
        registry.addResourceHandler("/upload/image*//**").addResourceLocations(
                "file:"+ueditorUploadFilePath);
    }

}*/
