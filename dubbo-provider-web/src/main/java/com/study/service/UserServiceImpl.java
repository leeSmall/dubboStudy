package com.study.service;

import org.apache.log4j.Logger;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.2", group = "user1")
public class UserServiceImpl implements UserService {
    
    private static Logger logger = Logger.getLogger(UserServiceImpl.class);
    
    public String login(String param) {
        logger.info("UserServiceImpl.login  begin!");
        return "用户已经登录成功！·~~~~~~~~~~~~~~~~~~";
    }
    
}
