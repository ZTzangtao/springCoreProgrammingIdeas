package com.zt.dependency.lookup;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link BeanCreationException}示例
 *
 * @author Tommy
 * @date 2020/7/19 10:25 下午
 */
public class BeanCreationExceptionDemo {

    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册BeanDefinition Bean Class是一个 PoJo 普通类，不过初始化方法回调时抛出异常
        BeanDefinitionBuilder beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(PoJo.class);
        applicationContext.registerBeanDefinition("beanName",beanDefinition.getBeanDefinition());
        //启动上下文
        applicationContext.refresh();
        //显示的关闭Spring 应用上下文
        applicationContext.close();
    }

    static class PoJo implements InitializingBean{

        //CommonAnnotationBeanPostProcessor
        @PostConstruct
        public void init() throws Throwable{
            throw new Exception("init(): For purposes...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet(): For purposes...");
        }
    }

}
