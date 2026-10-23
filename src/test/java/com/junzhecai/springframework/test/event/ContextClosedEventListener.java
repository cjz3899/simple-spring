package com.junzhecai.springframework.test.event;

import com.junzhecai.springframework.context.ApplicationListener;
import com.junzhecai.springframework.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }
}
