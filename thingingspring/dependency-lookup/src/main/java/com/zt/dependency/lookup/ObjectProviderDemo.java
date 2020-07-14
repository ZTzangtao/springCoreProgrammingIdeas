package com.zt.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 通过 @{@link ObjectProvider} 进行依赖查找
 *
 * @author Tommy
 * @date 2020/7/14 10:30 下午
 */
public class ObjectProviderDemo { //@Configuration 是非必须的注解

    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册ObjectProviderDemo Class(配置类)
        applicationContext.register(ObjectProviderDemo.class);

        //启动上下文
        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);

        //显示的关闭Spring 应用上下文
        applicationContext.close();
    }

    @Bean
    public String helloWorld(){
        return "Hello,world";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }
}
