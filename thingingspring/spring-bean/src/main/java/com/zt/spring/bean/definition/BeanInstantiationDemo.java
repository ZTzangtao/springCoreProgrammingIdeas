package com.zt.spring.bean.definition;

import com.zt.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean实例化
 * @author Tommy
 * @date 2020/7/10 9:28 下午
 */
public class BeanInstantiationDemo {
    public static void main(String[] args) {
        //配置xml文件，
        //启动spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/bean-Instantiation-context.xml");
        User userStatic = beanFactory.getBean("user-by-static-method", User.class);
        User userInstance = beanFactory.getBean("user-by-instance-method", User.class);
        User userFactory = beanFactory.getBean("user-by-factory-bean", User.class);

        System.out.println(userInstance);
        System.out.println(userStatic);
        System.out.println(userInstance == userStatic);
        System.out.println(userFactory);
        System.out.println(userFactory == userStatic);
    }


}
