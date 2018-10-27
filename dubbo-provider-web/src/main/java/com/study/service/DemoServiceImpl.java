package com.study.service;

/**
 * 
 * @Description: 泛化调用接口实现类
 * @author leeSmall
 * @date 2018年10月23日
 *
 */
public class DemoServiceImpl implements DemoService {
    public String eat(Long id) {
    	System.out.println("泛化调用");
        return "泛化调用" + id;
    }
}
