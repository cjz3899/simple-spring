package com.junzhecai.springframework.context.event;

import com.junzhecai.springframework.context.ApplicationEvent;
import com.junzhecai.springframework.context.ApplicationListener;

public interface ApplicationEventMulticaster {
    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
