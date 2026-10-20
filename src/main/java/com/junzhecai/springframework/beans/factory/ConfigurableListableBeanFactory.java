package com.junzhecai.springframework.beans.factory;

import com.junzhecai.springframework.beans.BeansException;
import com.junzhecai.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.junzhecai.springframework.beans.factory.config.BeanDefinition;
import com.junzhecai.springframework.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;
}
