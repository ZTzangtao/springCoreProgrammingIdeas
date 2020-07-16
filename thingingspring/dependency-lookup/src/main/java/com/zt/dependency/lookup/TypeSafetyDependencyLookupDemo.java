package com.zt.dependency.lookup;

import com.zt.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全 依赖查找示例
 *
 * @author Tommy
 * @date 2020/7/16 10:25 下午
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册ObjectProviderDemo Class(配置类)
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        //启动上下文
        applicationContext.refresh();

        //演示beanFactory#getBean 方法的安全性
        displayBeanFactoryGetBean(applicationContext);

        //演示ObjectFactory#getObject 方法的安全性
        displayObjectFactoryGetObject(applicationContext);

        //演示ObjectProvider#getIfAvailable方法的安全性
        displayObjectProviderIfAvailable(applicationContext);

        //演示ListableBeanFactory#getbeansOfType方法的安全性
        displayListableBeanFactoryGetBeansOfType(applicationContext);

        //演示ObjectProvider Stream 方法的安全性
        displayObjectProviderStreamOps(applicationContext);

        //显示的关闭Spring 应用上下文
        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps",()->userObjectProvider.forEach(System.out::println));

    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {
        printBeansException("displayListableBeanFactoryGetBeansOfType",()->beanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable",()->userObjectProvider.getIfAvailable());
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        //ObjectProvider is ObjectFactory
        ObjectFactory<User> objectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject",()->objectFactory.getObject());
    }

    public static void displayBeanFactoryGetBean(BeanFactory beanFactory){
//        try {
//            beanFactory.getBean(User.class);
//        }catch (BeansException e){
//            e.printStackTrace();
//
//        }
        //recode
        printBeansException("displayBeanFactoryGetBean",()-> beanFactory.getBean(User.class));

    }
    private static void printBeansException(String source, Runnable runnable){
        System.err.println("===============================================");
        System.err.println("source from : "+ source );
        try{
            runnable.run();
        }catch (BeansException e){
            e.printStackTrace();
        }
    }
}
