package com.zt.spring.bean.definition;

import com.zt.spring.bean.factory.DefaultUserFactory;
import com.zt.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean 初始化 demo
 *
 * @author Tommy
 * @date 2020/7/11 10:35 下午
 */
@Configuration //Configuration Class
public class BeanInitializationDemo {

    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class(配置类)
        applicationContext.register(BeanInitializationDemo.class);
        //启动spring应用上下文
        applicationContext.refresh();

        //依赖查找UserFactory
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

        //关闭spring应用上下文
        applicationContext.close();
    }

    @Bean(initMethod = "initUserFactory")
    public UserFactory userFactory(){
        return  new DefaultUserFactory();
    }

}
