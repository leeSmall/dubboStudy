package com.study.frameworkservice;

public class FrameworkTestServiceImpl implements FrameworkTestService {
    
    public String sleep(String param) {
        System.out.println("Provider->FrameworkTestServiceImpl->sleep->"
                + param);
        return "Provider->FrameworkTestServiceImpl->sleep->" + param;
    }
    
}
