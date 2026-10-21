package com.junzhecai.springframework.test;

import com.junzhecai.springframework.context.support.ClassPathXmlApplicationContext;
import com.junzhecai.springframework.test.bean.UserService_04;
import org.junit.jupiter.api.Test;

public class ApiTest5 {
    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService_04 userService = applicationContext.getBean("userService", UserService_04.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }
}
