package com.junzhecai.springframework.beans.factory.config;

import com.junzhecai.springframework.beans.BeansException;
import com.junzhecai.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
 */
public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
