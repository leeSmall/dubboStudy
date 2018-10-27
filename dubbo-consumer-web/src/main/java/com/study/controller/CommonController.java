package com.study.controller;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.study.callback.CallbackListener;
import com.study.callback.CallbackService;
import com.study.event.Common;
import com.study.mock.MockService;
import com.study.service.AnnotationDubboTest;
import com.study.service.AsyncService;
import com.study.service.CacheService;
import com.study.service.UserService;
import com.study.service.ValidationParameter;
import com.study.service.ValidationService;
import com.study.stub.StubService;
import com.study.test.service.DubboTestService;


/**
 * 
 * @Description: dubbo消费端测试control
 * @author leeSmall
 * @date 2018年10月23日
 *
 */
@Controller
@RequestMapping("/common")
public class CommonController implements ApplicationContextAware {
    
    private static Logger logger = Logger.getLogger(CommonController.class);
    
    //注解示例begin
    @Reference(check = false, timeout = 100000, version = "1.2.3")
    AnnotationDubboTest annotationdubbo;
    
    @RequestMapping("/annotationdubbo")
    public @ResponseBody String annotationdubbo() {
        annotationdubbo.eat("我是dubbo的注解测试control");
        return "annotationdubbo";
    }
    //注解示例end
    
    //服务分组示例begin
    @Autowired
    @Qualifier("dubboTestServiceImpl1")
    DubboTestService dubboService1;
    
    @Autowired
    @Qualifier("dubboTestServiceImpl2")
    DubboTestService dubboService2;
    
    @RequestMapping("/dubboTest")
    public @ResponseBody String dubboTest() {
        dubboService1.eat("服务分组示例！");
        dubboService2.eat("服务分组示例！");
        return "dubboTest";
    }
    //服务分组示例end
    
    //服务分组注解实现示例begin
    @Reference(version = "1.0.2", check = false, group = "user1")
    UserService usrService;
    
    @Reference(version = "1.0.2", check = false, group = "user2")
    UserService usr2Service;
    
    @RequestMapping("/grouplogin1")
    public @ResponseBody String login() {
        logger.info(usrService.login("服务分组注解实现"));
        logger.info(usr2Service.login("服务分组注解实现"));
        return "成功";
    }
    //服务分组注解实现示例end
    
    ApplicationContext applicationContext;
    
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    //参数验证begin
    @Autowired
    ValidationService validationService;
    
    @RequestMapping("/validation")
    public @ResponseBody String validation() {
        // Save OK
        ValidationParameter parameter = new ValidationParameter();
        parameter.setName("leeSmall");
        parameter.setEmail("leeSmall@qq.com");
        parameter.setAge(50);
        parameter.setLoginDate(new Date(System.currentTimeMillis() - 1000000));
        parameter.setExpiryDate(new Date(System.currentTimeMillis() + 1000000));
        validationService.save(parameter);
        System.out.println("Validation Save OK");
        
        // Save Error
        try {
            parameter = new ValidationParameter();
            validationService.save(parameter);
            System.err.println("Validation Save ERROR");
        }
        catch (RpcException e) {
            ConstraintViolationException ve = (ConstraintViolationException)e.getCause();
            Set<ConstraintViolation<?>> violations = ve.getConstraintViolations();
            System.out.println(violations);
        }
        
        // Delete OK
        validationService.delete(2, "abc");
        System.out.println("Validation Delete OK");
        
        // Delete Error
        try {
            validationService.delete(0, "abc");
            System.err.println("Validation Delete ERROR");
        }
        catch (RpcException e) {
            ConstraintViolationException ve = (ConstraintViolationException)e.getCause();
            Set<ConstraintViolation<?>> violations = ve.getConstraintViolations();
            System.out.println(violations);
        }
        return "OK";
    }
    //参数验证end
    
    //结果缓存begin
    @Autowired
    CacheService cacheService;
    
    @RequestMapping("/cache")
    public @ResponseBody String cache() {
        // 测试缓存生效，多次调用返回同样的结果。(服务器端自增长返回值)
        String fix = null;
        for (int i = 0; i < 5; i++) {
            String result = cacheService.findCache("0");
            if (fix == null || fix.equals(result)) {
                System.out.println("OK: " + result);
            }
            else {
                System.err.println("ERROR: " + result);
            }
            fix = result;
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // LRU的缺省cache.size为1000，执行1001次，会把第一次请求的缓存结果清除掉
        for (int n = 0; n < 1001; n++) {
            String pre = null;
            for (int i = 0; i < 10; i++) {
                String result = cacheService.findCache(String.valueOf(n));
                if (pre != null && !pre.equals(result)) {
                    System.err.println("ERROR: " + result);
                }
                pre = result;
            }
        }
        
        // 测试LRU有移除最开始的一个缓存项
        String result = cacheService.findCache("0");
        if (fix != null && !fix.equals(result)) {
            System.out.println("OK: " + result);
        }
        else {
            System.err.println("ERROR: " + result);
        }
        return "OK";
    }
    //结果缓存end
    
    //泛化调用begin
    @RequestMapping("/fanhua")
    public @ResponseBody String fanhuayinyong() {
        GenericService demoService = (GenericService)applicationContext.getBean("demoService");
        Object result = demoService.$invoke("eat", new String[] { "java.lang.Long" }, new Object[]{ 1L });
        System.out.println(result);
        return "OK";
    }
    //泛化调用end
    
    //实现泛化调用begin
    @RequestMapping("/fanhuashixian")
    public @ResponseBody String fanhuashixian() {
        GenericService barService = (GenericService)applicationContext.getBean("genericService");
        Object result = barService.$invoke("login",
                new String[] {"java.lang.String"},
                new Object[] {"World"});
        System.out.println(result);
        return "OK";
    }
    //实现泛化调用end
    

    //回声测试begin
    @RequestMapping("/huisheng")
    public @ResponseBody String huisheng() {
        CacheService barService = (CacheService)applicationContext.getBean("cacheService");
        EchoService echoService = (EchoService)barService; // 强制转型为EchoService
        // 回声测试可用性
        String status = (String)echoService.$echo("OK");
        if(status.equals("OK")) {
        	System.out.println("缓存接口服务可用");
        }
        assert (status.equals("OK"));
        return "OK";
    }
    //回声测试end
    
    //异步调用begin
    @Autowired
    AsyncService asyncService;
    
    
    @RequestMapping("/async")
    public @ResponseBody String async() {
        String result = asyncService.sayHello("我是异步调用客户端");
        System.out.println(result);
        // 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future
        Future<String> fooFuture = RpcContext.getContext().getFuture();
        // 如果result已返回，直接拿到返回值，否则线程wait住，等待result返回后，线程会被notify唤醒
        try {
        	//调用get方法会阻塞在这里直到拿到结果
            result = fooFuture.get();
            System.out.println(result);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "OK";
    }
    //异步调用end
    
    //参数回调begin
    @Autowired
    CallbackService callbackService;
    
    @RequestMapping("/callback")
    public @ResponseBody String callback() {
        callbackService.addListener("foo.bar", new CallbackListener() {
            public void changed(String msg) {
                System.out.println("callback1:" + msg);
            }
        });
        return "OK";
    }
    //参数回调end
    
    //事件通知begin
    @Autowired
    Common common;
    
    
    @RequestMapping("/event")
    public @ResponseBody String event() {
        String result = common.eat("jdksk");
        System.out.println(result);
        return "OK";
    }
    //事件通知end

    //本地存根begin
    @Autowired
    StubService stub;
    
    @RequestMapping("/stub")
    public @ResponseBody String stub() {
    	String result = stub.stub("eee");
    	System.out.println(result);
        return "OK";
    }
    //本地存根end 
    
    //本地伪装begin
    @Autowired
    MockService mock;
    
    @RequestMapping("/mock")
    public @ResponseBody String mock() {
    	String result = mock.mock("vvvv");
    	System.out.println(result);
        return "OK";
    }
    //本地伪装end
    
   
    

}
