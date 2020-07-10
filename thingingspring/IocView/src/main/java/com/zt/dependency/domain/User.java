package com.zt.dependency.domain;

import lombok.Data;

@Data
public class User {
    private String name;

    private int age;

    private  int id;

    public static User createUser(){
        User user = new User();
        user.setId(2);
        user.setName("zt-tommy");
        return user;
    }
}
