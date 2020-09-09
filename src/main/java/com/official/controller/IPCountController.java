package com.official.controller;

import com.official.bean.ResponseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2020/9/9.
 */
@RestController
@RequestMapping("/IPCount")
public class IPCountController {
    private final long baseNum = 1234;
    @GetMapping("/num")
    public ResponseBean getIPCount(HttpServletRequest request){
        //String remoteAddr = request.getRemoteAddr();
        //String localAddr = request.getLocalAddr();
        String sessionId = request.getSession().getId();
        Jedis jedis = new Jedis();
        jedis.pfadd("ipcount",sessionId);
        System.out.print("IP地址为：");
        long total = jedis.pfcount("ipcount") + baseNum;
        return new ResponseBean(true,200,"查询成功！",total);
    }
}
