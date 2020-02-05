package com.official.controller;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.official.bean.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;

@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {
        
	  @Autowired
	  private DefaultKaptcha defaultKaptcha;
	  
	  @RequestMapping("/getImage")
	  public void defaultKaptcha(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception{ 
		  httpServletResponse.setDateHeader("Expires", 0);

		  httpServletResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

		  httpServletResponse.addHeader("Cache-Control", "post-check=0, pre-check=0");

		  httpServletResponse.setHeader("Pragma", "no-cache");

		  httpServletResponse.setContentType("image/jpeg");

	        String capText = defaultKaptcha.createText();

	        //将验证码存到session
	        httpServletRequest.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
	        System.out.println("获取验证码"+httpServletRequest.getSession().getId());
	        BufferedImage bi = defaultKaptcha.createImage(capText);
	        ServletOutputStream out = httpServletResponse.getOutputStream();
	        // write the data out
	        ImageIO.write(bi, "jpg", out);
	        try {
	            out.flush();
	        } finally {
	            out.close();
	        }
				
		   }
		 
	  @RequestMapping("/checkCode")
	  public ResponseBean checkCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String code)   {
		  System.out.println("检查验证码"+httpServletRequest.getSession().getId());
//		  //从session中拿出存放的验证码跟前端传递过来的进行比较
 		  String rightCode = (String) httpServletRequest.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY); 
 		  System.out.println(code);
		  if (rightCode.equals(code)) {
		     return new ResponseBean(true, 200,"验证成功", true);	
		 }else {
			 return new ResponseBean(true, 200,"验证码错误", false);	
		}
		 
		  }

	  }

