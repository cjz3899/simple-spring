package com.junzhecai.springframework.test;

import com.junzhecai.springframework.beans.BeansException;
import com.junzhecai.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.junzhecai.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.junzhecai.springframework.test.bean.UserService_02;
import org.junit.jupiter.api.Test;

public class ApiTest3 {

    @Test
    public void test_xml() throws BeansException {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取Bean对象调用方法
        UserService_02 userService = beanFactory.getBean("userService", UserService_02.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }
}
