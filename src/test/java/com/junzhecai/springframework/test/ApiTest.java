package com.junzhecai.springframework.test;

import com.junzhecai.springframework.beans.BeansException;
import com.junzhecai.springframework.beans.factory.config.BeanDefinition;
import com.junzhecai.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.junzhecai.springframework.test.bean.UserService;
import org.junit.jupiter.api.Test;

public class ApiTest {
    @Test
    public void test_BeanFactory01() throws BeansException {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        // 4.第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }

    @Test
    public void test_BeanFactory02() throws BeansException {
        //初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        //获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "cjz");
        userService.queryUserInfo();
    }
}
