package com.junzhecai.springframework.context;

import com.junzhecai.springframework.beans.core.io.ResourceLoader;
import com.junzhecai.springframework.beans.factory.HierarchicalBeanFactory;
import com.junzhecai.springframework.beans.factory.ListableBeanFactory;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
