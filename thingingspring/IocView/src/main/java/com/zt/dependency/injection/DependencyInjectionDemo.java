package com.zt.dependency.injection;

import com.zt.dependency.annotion.Super;
import com.zt.dependency.domain.User;
import com.zt.dependency.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

public class DependencyInjectionDemo {

    public static void main(String[] args) {
        //配置xml文件，
        //启动spring应用上下文
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lnjection-context.xml");
        //ApplicationContext是BeanFactory的子接口
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lnjection-context.xml");

        //依赖来源一： 自定义的bean
        UserRepository user = (UserRepository) applicationContext.getBean("userRepository");
//        System.out.println("实时"+user);
        //依赖来源二： 依赖注入（内建依赖）
        System.out.println(user.getBeanFactory());

        //依赖查找(错误)
//        System.out.println(beanFactory.getBean(BeanFactory.class));
        ObjectFactory objectFactory = user.getObjectFactory();
        System.out.println(objectFactory.getObject() == applicationContext);

        //依赖来源三： 容器内建 Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println("获取Environment类型的bean："+environment);
    }

    private static void whoIsIocContainer(UserRepository userRepository ,ApplicationContext applicationContext ){
        //ConfigurableApplicationContext <- ApplicationContext <- BeanFactory

        //ConfigurableApplicaionContext #getBeanFactory()

//In short, the BeanFactory provides the configuration framework and basic functionality,
// and the ApplicationContext adds more enterprise-specific functionality.
// The ApplicationContext is a complete superset of the BeanFactory and is used exclusively in this chapter in descriptions of Spring’s IoC container.
        //更简单的集成与spring的AOP特色
        //Easier integration with Spring’s AOP features
        //消息资源处理（用于国际化）
        //Message resource handling (for use in internationalization)
        //事件发布
        //Event publication
        //特定于应用程序层的上下文，如用于web应用程序的WebApplicationContext。
        //Application-layer specific contexts such as the WebApplicationContext for use in web applications.


        //为什么这个表达式不成立？
        System.out.println(userRepository.getBeanFactory() == applicationContext);


    }


}
