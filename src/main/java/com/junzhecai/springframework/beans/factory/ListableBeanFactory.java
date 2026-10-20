package com.junzhecai.springframework.beans.factory;

import com.junzhecai.springframework.beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {
    /**
     * 按照类型返回 Bean 实例
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();

}
