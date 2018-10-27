package com.study.event;

/**
 * 
 * @Description: 事件通知接口实现类
 * @author leeSmall
 * @date 2018年10月25日
 *
 */
public class NotifyImpl implements Notify {
    
	//调用之前
    public void oninvoke(String msg) {
    	System.out.println("======oninvoke======, param: " + msg);
	}
	
    //调用之后
    public void onreturn(String msg) {
        System.out.println("======onreturn======, param: " + msg);
    }
    
     // 出现异常
    public void onthrow(Throwable ex) {
        System.out.println("======onthrow======, param: " + ex);
    }

	
    
}
