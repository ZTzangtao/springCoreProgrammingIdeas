package com.zt.dependency.injection;

import com.zt.dependency.annotion.Super;
import com.zt.dependency.domain.User;
import com.zt.dependency.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class DependencyInjectionDemo {

    public static void main(String[] args) {
        //配置xml文件，
        //启动spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lnjection-context.xml");
        UserRepository user = (UserRepository) beanFactory.getBean("userRepository");
//        System.out.println("实时"+user);
        //依赖注入
        System.out.println(user.getBeanFactory());
//        System.out.println(user.getBeanFactory() == beanFactory);

        //依赖查找(错误)
//        System.out.println(beanFactory.getBean(BeanFactory.class));
        ObjectFactory objectFactory = user.getObjectFactory();
        System.out.println(objectFactory.getObject() == beanFactory);

    }

}
