package com.junzhecai.springframework.beans.factory;

public interface DisposableBean {
    /**
     * Bean销毁时调用
     */
    void destroy() throws Exception;
}
