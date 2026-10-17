package com.junzhecai.springframework.beans.factory.support;

import com.junzhecai.springframework.beans.BeansException;
import com.junzhecai.springframework.beans.core.io.Resource;
import com.junzhecai.springframework.beans.core.io.ResourceLoader;

public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
