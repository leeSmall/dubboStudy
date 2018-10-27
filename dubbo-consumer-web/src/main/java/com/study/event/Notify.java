package com.study.event;

/**
 * 
 * @Description: 事件通知接口
 * @author leeSmall
 * @date 2018年10月25日
 *
 */
public interface Notify {
	
	//调用之前
	public void oninvoke(String name); 
	
	//调用之后
    public void onreturn(String msg);
    
    // 出现异常
    public void onthrow(Throwable ex);
}
