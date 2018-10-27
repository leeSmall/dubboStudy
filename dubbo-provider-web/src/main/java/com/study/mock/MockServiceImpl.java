package com.study.mock;

/**
 * 
 * @Description: 本地伪装接口实现类
 * @author leeSmall
 * @date 2018年10月25日
 *
 */
public class MockServiceImpl implements MockService {
    
    public String mock(String param) {
        System.out.println("provider MockServiceImpl mock");
        return "provider MockServiceImpl mock";
    }
    
}
