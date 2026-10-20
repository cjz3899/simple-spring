package com.junzhecai.springframework.test;

import com.junzhecai.springframework.beans.BeansException;
import com.junzhecai.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.junzhecai.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.junzhecai.springframework.context.support.ClassPathXmlApplicationContext;
import com.junzhecai.springframework.test.bean.UserService_03;
import com.junzhecai.springframework.test.common.MyBeanFactoryPostProcessor;
import com.junzhecai.springframework.test.common.MyBeanPostProcessor;
import org.junit.jupiter.api.Test;

public class ApiTest4 {
    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor() throws BeansException {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean实例化之后，修改 Bean 属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5. 获取Bean对象调用方法
        UserService_03 userService = beanFactory.getBean("userService", UserService_03.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");

        // 2. 获取Bean对象调用方法
        UserService_03 userService = applicationContext.getBean("userService", UserService_03.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }
}
