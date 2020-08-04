package com.zt.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency作为依赖来源
 *
 * @author Tommy
 * @date 2020/8/4 9:48 下午
 */
public class ResolvableDependencySourceDemo {
    @Autowired
    private String value;

    @PostConstruct
    public void init(){
        System.out.println(value);
    }


    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ResolvableDependencySourceDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            //注册Resolvable Dependency
            beanFactory.registerResolvableDependency(String.class,"Hello,World");

        });

        //启动上下文
        applicationContext.refresh();

        //显示的关闭Spring 应用上下文
        applicationContext.close();
    }
}
