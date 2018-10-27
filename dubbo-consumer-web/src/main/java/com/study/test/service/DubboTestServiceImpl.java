package com.study.test.service;

public class DubboTestServiceImpl implements DubboTestService {
    
    public String eat(String param) {
        
        System.out.println("-----------dubbo service test------------" + param);
        return "-----------dubbo service test------------";
    }
    
}
