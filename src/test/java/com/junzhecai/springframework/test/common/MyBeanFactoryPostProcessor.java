package com.junzhecai.springframework.test.common;

import com.junzhecai.springframework.beans.BeansException;
import com.junzhecai.springframework.beans.PropertyValue;
import com.junzhecai.springframework.beans.PropertyValues;
import com.junzhecai.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.junzhecai.springframework.beans.factory.config.BeanDefinition;
import com.junzhecai.springframework.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为B站"));
    }
}
