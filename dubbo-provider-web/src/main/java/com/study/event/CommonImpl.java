package com.study.event;

/**
 * 
 * @Description: 事件通知接口参与接口实现类
 * @author leeSmall
 * @date 2018年10月25日
 *
 */
public class CommonImpl implements Common {
    
    public String eat(String param) {
        
        System.out.println("CommonImpl eat");
        return "CommonImpl eat";
    }
    
}
