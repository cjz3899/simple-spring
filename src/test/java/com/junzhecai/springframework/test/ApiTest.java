package com.junzhecai.springframework.test;

import com.junzhecai.springframework.beans.BeansException;
import com.junzhecai.springframework.beans.PropertyValue;
import com.junzhecai.springframework.beans.PropertyValues;
import com.junzhecai.springframework.beans.factory.config.BeanDefinition;
import com.junzhecai.springframework.beans.factory.config.BeanReference;
import com.junzhecai.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.junzhecai.springframework.test.bean.UserDao;
import com.junzhecai.springframework.test.bean.UserService_01;
import com.junzhecai.springframework.test.bean.UserService_02;
import org.junit.jupiter.api.Test;

public class ApiTest {
    @Test
    public void test_BeanFactory01() throws BeansException {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService_01.class);
        beanFactory.registerBeanDefinition("userService01", beanDefinition);
        // 3.第一次获取 bean
        UserService_01 userService01 = (UserService_01) beanFactory.getBean("userService01");
        userService01.queryUserInfo();
        // 4.第二次获取 bean from Singleton
        UserService_01 userService_01_singleton = (UserService_01) beanFactory.getBean("userService01");
        userService_01_singleton.queryUserInfo();
    }

    @Test
    public void test_BeanFactory02() throws BeansException {
        //初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService_01.class);
        beanFactory.registerBeanDefinition("userService01", beanDefinition);
        //获取bean
        UserService_01 userService01 = (UserService_01) beanFactory.getBean("userService01", "cjz");
        userService01.queryUserInfo();
    }

    @Test
    public void test_BeanFactory03() throws BeansException {
        //初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //UserDao注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));
        //UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        //UserService注入Bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService_02.class, propertyValues);
        beanFactory.registerBeanDefinition("userService02", beanDefinition);
        //获取bean
        UserService_02 userService02 = (UserService_02) beanFactory.getBean("userService02");
        userService02.queryUserInfo();
    }
}
