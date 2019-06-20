package com.test.cn.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.test.cn.dubbo.api.SpringbootDubboService;
import com.test.cn.pojo.jpa.SpringBootUserInfo;
import com.test.cn.service.jpa.SpringBootUserInfoService;
import com.test.cn.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private RedisUtil redisUtil;
    @Reference(check = false)
    private SpringbootDubboService dubboService;
    @Autowired
    private SpringBootUserInfoService userInfoService;

    @RequestMapping("/hello")
    public String index(){
        return "Hello world";
    }

    @RequestMapping("/testRedis")
    public String testRedis(){
        redisUtil.set("testRedis","hello redis");
        return redisUtil.get("testRedis")+"";
    }

    @RequestMapping("/testDubbo")
    public String testDubbo(){
        return dubboService.springbootDubboTest();
    }

    @RequestMapping("/testJpa")
    public String testJpa(){
        SpringBootUserInfo userInfo = new SpringBootUserInfo();
        userInfo.setUsername("star1");
        return userInfoService.findUserInfo(userInfo).toString();
    }
}
