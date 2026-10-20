package com.junzhecai.springframework.context;

import com.junzhecai.springframework.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext {
    /**
     * 刷新容器
     */
    void refresh() throws BeansException;
}
