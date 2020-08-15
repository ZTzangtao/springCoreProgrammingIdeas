package com.zt.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解BeanDefinition 解析示例
 *
 * @author Tommy
 * @date 2020/8/15 11:39 下午
 */
public class AnnotatedBeanDefinitionParsingDemo {

    public static void main(String[] args) {
        //BeanDefinitionRegistry 它的第一个实现类是
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //基于java注解的 AnnotatedBeanDefinitionReader 的实现
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);

        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
        //注册当前类  （非 @Component class）
        beanDefinitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();
        int beanDefinitionCount = beanDefinitionCountAfter - beanDefinitionCountBefore;
        System.out.println("已加载 BeanDefinition 的数量：" + beanDefinitionCount);
        //普通的Class作为component 注册到spring ioc 容器后，通常bean名称为该类的小写，即annotatedBeanDefinitionParsingDemo
        //Bean名称生成 来自于BeanNameGenerator       注解实现 AnnotationBeanNameGenerator
        AnnotatedBeanDefinitionParsingDemo demo = beanFactory.getBean("annotatedBeanDefinitionParsingDemo",AnnotatedBeanDefinitionParsingDemo.class);

        System.out.println(demo);
    }
}
