package com.study.registry;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.study.service.UserService;
import com.study.service.UserServiceImpl;

public class UserRegistry {
    
    public void registry() {
        UserService userService = new UserServiceImpl();
        ApplicationConfig application = new ApplicationConfig();
        application.setName("dubbo-provider-web");
        
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("127.0.0.1:2181");
        
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(29014);
        protocol.setThreads(200);
        
        ServiceConfig<UserService> service = new ServiceConfig<UserService>();
        service.setApplication(application);
        service.setRegistry(registry);
        service.setProtocol(protocol);
        service.setInterface(UserService.class);
        service.setRef(userService);
        service.setVersion("1.0.0");
        
        service.export();
    }
    
    public static void main(String[] args) {
        new UserRegistry().registry();
    }
}
