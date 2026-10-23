package com.junzhecai.springframework.context.event;

import com.junzhecai.springframework.context.ApplicationContext;
import com.junzhecai.springframework.context.ApplicationEvent;

public class ApplicationContextEvent extends ApplicationEvent {
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
