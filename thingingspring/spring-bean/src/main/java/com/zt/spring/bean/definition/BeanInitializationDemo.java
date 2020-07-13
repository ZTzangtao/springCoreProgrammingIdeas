package com.zt.spring.bean.definition;

import com.zt.spring.bean.factory.DefaultUserFactory;
import com.zt.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean 初始化 demo
 *
 * @author Tommy
 * @date 2020/7/11 10:35 下午
 */
@Configuration //Configuration Class
public class BeanInitializationDemo {

    public static void main(String[] args) throws InterruptedException {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class(配置类)
        applicationContext.register(BeanInitializationDemo.class);
        //启动spring应用上下文
        applicationContext.refresh();

        //非延迟初始化在Spring应用上下文启动完成后，被初始化
        System.out.println("Spring应用上下文已启动...");

        //依赖查找UserFactory
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);

        System.out.println("Spring应用上下文 准备关闭...");

        //关闭spring应用上下文
        applicationContext.close();
        System.out.println("Spring应用上下文 已关闭...");

        Thread.sleep(5000L);
        //强行触发gc
        System.gc();
        Thread.sleep(5000L);
    }

    @Bean(initMethod = "initUserFactory",destroyMethod = "doDestroy")
    @Lazy(value = true)
    public UserFactory userFactory(){
        return  new DefaultUserFactory();
    }




}
