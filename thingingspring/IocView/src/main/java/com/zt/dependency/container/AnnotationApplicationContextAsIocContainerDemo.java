package com.zt.dependency.container;

import com.zt.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author zangtao
 *
 * 注解能力{@link org.springframework.context.ApplicationContext} 作为IOC容器示例
 */
@Configuration
public class AnnotationApplicationContextAsIocContainerDemo {

    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类作为配置类
        applicationContext.register(AnnotationApplicationContextAsIocContainerDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        //依赖查找
        lookupByCollectionType(applicationContext);
    }

    /**
     * 通过java注解方式 定义了一个bean
     *
     * @return
     */
    @Bean
    public User user(){
        User user = new User();
        user.setId(1);
        user.setName("zt");
        return user;
    }


    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有集合对象："+ users);
        }
    }



}
