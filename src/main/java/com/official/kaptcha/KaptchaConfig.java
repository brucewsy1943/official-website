package com.official.kaptcha;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@Component
public class KaptchaConfig {
            
	@Bean  
    public DefaultKaptcha getDefaultKaptcha(){  
        com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha = new com.google.code.kaptcha.impl.DefaultKaptcha();  
        Properties properties = new Properties();  
        properties.setProperty("kaptcha.border", "yes");  
        properties.setProperty("kaptcha.border.color", "white");  
        properties.setProperty("kaptcha.textproducer.font.color", "blue");  
        properties.setProperty("kaptcha.image.width", "130");  
        properties.setProperty("kaptcha.image.height", "40");  
        properties.setProperty("kaptcha.textproducer.font.size", "30");  
        properties.setProperty("kaptcha.session.key",Constants.KAPTCHA_SESSION_KEY);  
        properties.setProperty("kaptcha.textproducer.char.length", "4");  
        properties.setProperty("kaptcha.textproducer.font.names", "微软雅黑");  
        
        Config config = new Config(properties);  
        defaultKaptcha.setConfig(config);  
          
        return defaultKaptcha;  
    }  
	    
}
