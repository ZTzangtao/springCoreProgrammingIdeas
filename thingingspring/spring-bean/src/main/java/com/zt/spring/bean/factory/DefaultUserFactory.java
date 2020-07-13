package com.zt.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 默认 {@link UserFactory}实现
 *
 * @author Tommy
 * @date 2020/7/10 9:37 下午
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    //1、基于@PostConstruct 注解
    @PostConstruct
    public void init(){
        System.out.println("@PostConstruct : UserFactory初始化中...");
    }

    public void initUserFactory(){
        System.out.println("自定义初始化方法 initUserFactory() : UserFactory初始化中...");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean # afterPropertiesSet() : UserFactory初始化中...");
    }

    public void ztTest(){
        System.out.println("多帅啊！");
    }

    @PreDestroy
    public void PreDestroy(){
        System.out.println("@PreDestroy : UserFactory销毁中...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean # destroy() : UserFactory销毁中...");
    }

    public void doDestroy(){
        System.out.println("自定义销毁方法 doDestroy() : UserFactory销毁中...");
    }

    @Override
    public void finalize() throws Throwable {
        System.out.println("当前DefaultUserFactory对象正在被垃圾回收...");
    }
}
