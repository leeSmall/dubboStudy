package com.study.stub;

/**
 * 
 * @Description: 本地存根接口实现类
 * @author leeSmall
 * @date 2018年10月25日
 *
 */
public class StubServiceImpl implements StubService {
    
    public String stub(String param) {
        System.out.println("provider StubServiceImpl stub");
        return "provider StubServiceImpl stub";
    }
    
}
