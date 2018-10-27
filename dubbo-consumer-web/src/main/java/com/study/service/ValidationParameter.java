package com.study.service;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 
 * @Description: 参数验证实体
 * @author leeSamll
 * @date 2018年10月23日
 *
 */
public class ValidationParameter implements Serializable {
    
    private static final long serialVersionUID = 7158911668568000392L;
    
    // 不允许为空
    @NotNull
    // 长度或大小范围
    @Size(min = 2, max = 20)
    private String name;
    
    // 保存时不允许为空，更新时允许为空 ，表示不更新该字段
    @NotNull(groups = ValidationService.Save.class)
    //邮件格式校验
    @Pattern(regexp = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")
    private String email;
    
    // 最小值
    @Min(18)
    // 最大值
    @Max(100)
    private int age;
    
    // 必须为一个过去的时间
    @Past
    private Date loginDate;
    
    // 必须为一个未来的时间
    @Future
    private Date expiryDate;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public Date getLoginDate() {
        return loginDate;
    }
    
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
    
    public Date getExpiryDate() {
        return expiryDate;
    }
    
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    
}
