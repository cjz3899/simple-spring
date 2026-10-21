package com.junzhecai.springframework.context;

import com.junzhecai.springframework.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext {
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
