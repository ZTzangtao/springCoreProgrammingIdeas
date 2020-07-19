package com.zt.dependency.lookup;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link BeanInstantiationException} 演示
 *
 * @author Tommy
 * @date 2020/7/19 10:16 下午
 */
public class BeanInstantiationExceptionDemo {

    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册BeanDefinition Bean Class是一个 CharSequence 接口
        BeanDefinitionBuilder beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
        applicationContext.registerBeanDefinition("beanName",beanDefinition.getBeanDefinition());
        //启动上下文
        applicationContext.refresh();
        //显示的关闭Spring 应用上下文
        applicationContext.close();
    }


}
