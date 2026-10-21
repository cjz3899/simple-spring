package com.junzhecai.springframework.test;

import com.junzhecai.springframework.context.support.ClassPathXmlApplicationContext;
import com.junzhecai.springframework.test.bean.UserService_05;
import org.junit.jupiter.api.Test;

public class ApiTest6 {
    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService_05 userService = applicationContext.getBean("userService", UserService_05.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
        System.out.println("ApplicationContextAware：" + userService.getApplicationContext());
        System.out.println("BeanFactoryAware：" + userService.getBeanFactory());
    }

}
