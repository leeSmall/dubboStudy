package com.study.service;

import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * 
 * @Description: dubbo提供的泛化接口的实现类
 * @author leeSmall
 * @date 2018年10月24日
 *
 */
public class MyGenericService implements GenericService {
    
    public Object $invoke(String method, String[] parameterTypes, Object[] args)
            throws GenericException {
        System.out.println("泛化调用实现！");
        return "泛化调用实现";
    }
    
}
