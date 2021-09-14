package com.zt.dependency.domain;

import com.zt.dependency.enums.City;
import lombok.Data;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Properties;

/**
 * @author zangtao
 */
@Data
public class User implements BeanNameAware {
    private String name;

    private int age;

    private  int id;

    private City city;

    private City[] workCities;

    private List<City> lifeCities;

    private Resource configFileLocation;

    private Company company;

    private Properties context;

    private String contextAsText;

    //当前bean的名称
    private transient String beanName;

    public static User createUser(){
        User user = new User();
        user.setId(2);
        user.setName("zt-tommy");
        return user;
    }

    public void ztTest(){
        System.out.println("多帅啊！");
    }

    @PostConstruct
    public void init(){
        System.out.println("用户Bean["+beanName+"]对象初始化...");
    }

    @PreDestroy
    public void destroyBean(){
        System.out.println("用户Bean["+beanName+"]对象销毁...");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
