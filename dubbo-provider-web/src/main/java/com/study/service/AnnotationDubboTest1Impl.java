package com.study.service;

import com.alibaba.dubbo.config.annotation.Service;

@Service(timeout = 1000000, version = "1.2.4")
public class AnnotationDubboTest1Impl implements AnnotationDubboTest {
    
    public String eat(String param) {
        System.out.println("-----------AnnotationDubboTest1Impl service test------------"
                + param);
        return "-----------AnnotationDubboTest1Impl service test------------";
    }
    
}
