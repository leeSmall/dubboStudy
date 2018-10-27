package com.study.service;

import java.util.List;
import java.util.Map;

public interface UserService {
    
    public String login(String param);
    
    public String login(int a, int b, String c);
    
    public String[] mergeArray();
    
    public List<String> mergeList();
    
    public Map<String, String> mergeMap();
}
