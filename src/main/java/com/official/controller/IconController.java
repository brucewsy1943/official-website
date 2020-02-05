package com.official.controller;

import java.util.List;

import com.official.service.IconService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.official.bean.Icon;
import com.official.bean.ResponseBean;

@RequestMapping("/icon")
@RestController
public class IconController {
        
	   @Autowired
	   private IconService iconService;
	   
	   @RequiresAuthentication
		@RequestMapping("/getIcons")
		public ResponseBean getIcon(){
				 
			List<Icon> icons = iconService.selectAll();
			return new ResponseBean(true, 200,"[you are daidaishou]", icons);
			
		}
}
