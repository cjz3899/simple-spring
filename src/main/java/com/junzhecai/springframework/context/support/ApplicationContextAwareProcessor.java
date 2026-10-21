package com.junzhecai.springframework.context.support;

import com.junzhecai.springframework.beans.BeansException;
import com.junzhecai.springframework.beans.factory.config.BeanPostProcessor;
import com.junzhecai.springframework.context.ApplicationContext;
import com.junzhecai.springframework.context.ApplicationContextAware;

public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
