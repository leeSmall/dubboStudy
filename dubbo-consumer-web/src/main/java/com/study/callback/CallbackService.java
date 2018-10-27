package com.study.callback;

/**
 * 
 * @Description: 参数回调接口
 * @author leeSmall
 * @date 2018年10月25日
 *
 */
public interface CallbackService {
    
    void addListener(String key, CallbackListener listener);
    
}
