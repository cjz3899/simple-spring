package com.junzhecai.springframework.test;

import com.junzhecai.springframework.context.support.ClassPathXmlApplicationContext;
import com.junzhecai.springframework.test.event.CustomEvent;
import org.junit.jupiter.api.Test;

public class ApiTest8 {
    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_02.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }
}
