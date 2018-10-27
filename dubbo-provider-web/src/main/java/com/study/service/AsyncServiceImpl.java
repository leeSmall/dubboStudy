package com.study.service;


/**
 * 
 * @Description: 异步调用接口实现类
 * @author leeSmall
 * @date 2018年10月24日
 *
 */
public class AsyncServiceImpl implements AsyncService {
    
    public String sayHello(String name) {
        for (int i = 0; i < 5; i++) {
            System.out.println("async provider received: " + name);
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "hello, " + name;
    }
    
}