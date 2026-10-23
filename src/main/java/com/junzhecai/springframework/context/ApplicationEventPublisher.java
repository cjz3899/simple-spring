package com.junzhecai.springframework.context;

public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
