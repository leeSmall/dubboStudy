package com.study.stub;

/**
 * 
 * @Description: 本地存根Proxy实例
 * @author leeSmall
 * @date 2018年10月25日
 *
 */
public class LocalStubServiceProxy implements StubService {
    
    private StubService stubService;
    
    public LocalStubServiceProxy(StubService stubService) {
        this.stubService = stubService;
    }
    
    public String stub(String arg0) {
        System.out.println("我是localstub，我就相当于一个过滤器，在代理调用远程方法的时候，我先做一下拦截工作");
        
        try {
            return stubService.stub("xxx");
        }
        catch (Exception e) {
            System.out.println("远程调用出现异常了，我是本地stub，我要对远程服务进行伪装，达到服务降级的目的");
            return "远程调用出现异常了，我是本地stub，我要对远程服务进行伪装，达到服务降级的目的";
        }
    }
    
}
