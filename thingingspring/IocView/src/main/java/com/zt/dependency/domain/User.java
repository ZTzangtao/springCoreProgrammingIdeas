package com.zt.dependency.domain;

import com.zt.dependency.enums.City;
import lombok.Data;
import org.springframework.core.io.Resource;

import java.util.List;

@Data
public class User {
    private String name;

    private int age;

    private  int id;

    private City city;

    private City[] workCities;

    private List<City> lifeCities;

    private Resource configFileLocation;

    public static User createUser(){
        User user = new User();
        user.setId(2);
        user.setName("zt-tommy");
        return user;
    }

    public void ztTest(){
        System.out.println("多帅啊！");
    }
}
