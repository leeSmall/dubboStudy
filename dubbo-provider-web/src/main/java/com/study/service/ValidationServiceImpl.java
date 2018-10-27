package com.study.service;

/**
 * 
 * @Description: 参数验证接口实现类
 * @author leeSamll
 * @date 2018年10月23日
 *
 */
public class ValidationServiceImpl implements ValidationService {
    
    public void save(ValidationParameter parameter) {
        System.out.println("save");
    }
    
    public void update(ValidationParameter parameter) {
    }
    
    public void delete(long id, String operator) {
        System.out.println("delete");
    }
    
}
