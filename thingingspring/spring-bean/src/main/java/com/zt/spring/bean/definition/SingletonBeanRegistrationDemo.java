package com.zt.spring.bean.definition;

import com.zt.spring.bean.factory.DefaultUserFactory;
import com.zt.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体bean 注册实例
 *
 * @author Tommy
 * @date 2020/7/13 11:07 下午
 */
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //创建一个外部UserFactory 对象
        UserFactory userFactory = new DefaultUserFactory();
        SingletonBeanRegistry singletonBeanRegistry = applicationContext.getBeanFactory();
        //注册外部单例对象
        singletonBeanRegistry.registerSingleton("userFactory",userFactory);

        applicationContext.register(BeanInitializationDemo.class);
        //启动spring应用上下文
        applicationContext.refresh();

        //通过依赖查找的方式 来获取UserFactory
        UserFactory userFactoryByLookUp =applicationContext.getBean("userFactory",UserFactory.class);

        System.out.println("userFactory == userFactoryByLookUp ：" + (userFactory == userFactoryByLookUp));

        //关闭spring应用上下文
        applicationContext.close();

    }

}
