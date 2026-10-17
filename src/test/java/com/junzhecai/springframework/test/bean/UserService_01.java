package com.junzhecai.springframework.test.bean;

public class UserService_01 {
    private String name;

    public UserService_01() {
    }

    public UserService_01(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + name);
    }

    @Override
    public String toString() {
        return "UserService_01{" +
                "name='" + name + '\'' +
                '}';
    }
}
