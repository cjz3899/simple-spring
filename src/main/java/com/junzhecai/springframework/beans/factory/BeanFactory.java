package com.junzhecai.springframework.beans.factory;

import com.junzhecai.springframework.beans.BeansException;

public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    /**
     * 传递入参给构造函数实例化
     *
     * @param name bean名称
     * @param args 构造函数参数
     * @return bean实例
     */
    Object getBean(String name, Object... args) throws BeansException;

}
