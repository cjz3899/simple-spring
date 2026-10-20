package com.junzhecai.springframework.test.common;

import com.junzhecai.springframework.beans.BeansException;
import com.junzhecai.springframework.beans.factory.config.BeanPostProcessor;
import com.junzhecai.springframework.test.bean.UserService_03;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService_03 userService03 = (UserService_03) bean;
            userService03.setLocation("改为：上海");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
