package com.zt.dependency.domain;

/**
 * 公司类
 *
 * @Author: Tommy
 * @DATE: 2021/8/3
 */
public class Company {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}
