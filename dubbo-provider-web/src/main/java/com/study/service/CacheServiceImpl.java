package com.study.service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @Description: 结果缓存接口实现类
 * @author leeSmall
 * @date 2018年10月24日
 *
 */
public class CacheServiceImpl implements CacheService {
    
    private final AtomicInteger i = new AtomicInteger();
    
    public String findCache(String id) {
        System.out.println("request: " + id + ", response: "
                + i.getAndIncrement());
        return "request: " + id + ", response: " + i.getAndIncrement();
    }
}
