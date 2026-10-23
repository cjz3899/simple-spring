package com.junzhecai.springframework.context.event;

import com.junzhecai.springframework.beans.factory.BeanFactory;
import com.junzhecai.springframework.context.ApplicationEvent;
import com.junzhecai.springframework.context.ApplicationListener;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
