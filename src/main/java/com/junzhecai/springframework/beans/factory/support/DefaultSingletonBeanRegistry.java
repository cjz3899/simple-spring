package com.junzhecai.springframework.beans.factory.support;

import com.junzhecai.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;


public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object beanInstance) {
        singletonObjects.put(beanName, beanInstance);
    }
}
