package com.study.mock;

/**
 * 
 * @Description: 本地伪装代理实例
 * @author leeSmall
 * @date 2018年10月25日
 *
 */
public class LocalMockProxyService implements MockService {
    
    public String mock(String arg0) {
        System.out.println("local mock  做一下容错处理，这个就是服务降级");
        return "local mock  做一下容错处理，这个就是服务降级";
    }
}
