package com.zt.dependency.overview;

import com.zt.dependency.annotion.Super;
import com.zt.dependency.domain.SuperUser;
import com.zt.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class DependencyLookupDemo {

    public static void main(String[] args) {
        //配置xml文件，
        //启动spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
//        lookupByType(beanFactory);
        //按照类型查找集合对象
        lookupByCollectionType(beanFactory);
//        lookupRealTime(beanFactory);
//        lookupLazyTime(beanFactory);
        //通过注解查找
        lookupByAnnotionType(beanFactory);
    }

    private static void lookupByAnnotionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String,User> users =(Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找 标注@Super 所有集合对象"+ users);
        }
    }

    //
    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String,User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有集合对象"+ users);
        }
    }

    //根据类型查找
    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("根据类型："+user);


    }

    private static void lookupLazyTime(BeanFactory beanFactory) {
       ObjectFactory<User> objectFactory =(ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟"+user);
    }

    private static  void lookupRealTime( BeanFactory beanFactory ){
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时"+user);
    }
}
