package com.junzhecai.springframework.beans.factory;

import com.junzhecai.springframework.beans.BeansException;

public interface BeanFactory {

    Object getBean(String name) throws BeansException;

}
