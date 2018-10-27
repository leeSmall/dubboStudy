package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.service.UserService;

//@Controller
//@RequestMapping("/netty")
public class NettyController {
    @Autowired
    UserService userservice;
    
    @RequestMapping("/index")
    public @ResponseBody String index() {
        System.out.println(userservice.login("xxg"));
        return "成功";
    }
    
}
