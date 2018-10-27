package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.frameworkservice.FrameworkTestService;

//@Controller
//@RequestMapping("/framework")
public class FrameworkTestController {
    
    @Autowired
    FrameworkTestService service;
    
    @RequestMapping("/index")
    public @ResponseBody String index() {
        String result = service.sleep("my name is jack!!!!");
        System.out.println(result);
        return result;
    }
    
}
