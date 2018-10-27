package com.study.test.service;

public class DubboTestService1Impl implements DubboTestService {
    
    public String eat(String param) {
        
        System.out.println("-----------dubbo service test DubboTestService1Impl------------" + param);
        return "-----------dubbo service test DubboTestService1Impl------------";
    }
    
}
