package com.junzhecai.springframework.context.support;

import com.junzhecai.springframework.beans.BeansException;
import com.junzhecai.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.junzhecai.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }


    protected abstract String[] getConfigLocations();
}
