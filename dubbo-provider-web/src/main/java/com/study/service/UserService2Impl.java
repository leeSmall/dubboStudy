package com.study.service;

import org.apache.log4j.Logger;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.2", group = "user2")
public class UserService2Impl implements UserService {
    
    private static Logger logger = Logger.getLogger(UserService2Impl.class);
    
    public String login(String param) {
        logger.info("UserService2Impl.login  begin!22222");
        return "用户已经登录成功！·~~~~~~~~~~~~~~~~~~";
    }
    
    
}
