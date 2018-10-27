package com.study.service;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * 
* @Description: dubbo注解测试的接口的实现类
* @author liguangsheng
* @date 2018年10月23日
*
 */
@Service(timeout = 1000000, version = "1.2.3")
public class AnnotationDubboTestImpl implements AnnotationDubboTest {
    
    public String eat(String param) {
        System.out.println("-----------AnnotationDubboTestImpl service test------------"
                + param);
        return "-----------AnnotationDubboTestImpl service test------------";
    }
    
}
