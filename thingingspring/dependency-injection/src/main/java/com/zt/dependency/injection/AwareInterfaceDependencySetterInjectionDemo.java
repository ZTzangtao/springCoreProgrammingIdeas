package com.zt.dependency.injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于 {@link Aware} 接口回调的依赖注入示例
 *
 * @author Tommy
 * @date 2020/7/22 9:57 下午
 */
public class AwareInterfaceDependencySetterInjectionDemo implements BeanFactoryAware, ApplicationContextAware {

    private static BeanFactory beanFactory;

    private static ApplicationContext applicationContext;


    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册Configuration Class(配置类) -> Spring Bean
        context.register(AwareInterfaceDependencySetterInjectionDemo.class);
        //启动上下文
        context.refresh();
        System.out.println(beanFactory == context.getBeanFactory());
        System.out.println(context == applicationContext);

        //显示的关闭Spring 应用上下文
        context.close();

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceDependencySetterInjectionDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependencySetterInjectionDemo.applicationContext = applicationContext;
    }
}
