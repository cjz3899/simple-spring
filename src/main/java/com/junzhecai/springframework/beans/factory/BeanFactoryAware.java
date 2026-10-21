package com.junzhecai.springframework.beans.factory;

import com.junzhecai.springframework.beans.BeansException;

public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
