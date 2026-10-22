package com.junzhecai.springframework.test;

import com.junzhecai.springframework.context.support.ClassPathXmlApplicationContext;
import com.junzhecai.springframework.test.bean.UserService_06;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

public class ApiTest7 {
    @Test
    public void test_prototype() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService_06 userService01 = applicationContext.getBean("userService", UserService_06.class);
        UserService_06 userService02 = applicationContext.getBean("userService", UserService_06.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);

        // 4. 打印十六进制哈希
        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());

    }

    @Test
    public void test_factory_bean() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 调用代理方法
        UserService_06 userService = applicationContext.getBean("userService", UserService_06.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

}
