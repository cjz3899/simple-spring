package com.junzhecai.springframework.context;

import com.junzhecai.springframework.beans.BeansException;
import com.junzhecai.springframework.beans.factory.Aware;

public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
