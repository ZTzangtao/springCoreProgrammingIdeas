package com.zt.dependency.lookup;

import com.zt.dependency.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 通过 @{@link ObjectProvider} 进行依赖查找
 *
 * @author Tommy
 * @date 2020/7/14 10:30 下午
 */
public class ObjectProviderDemo { //@Configuration 是非必须的注解

    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册ObjectProviderDemo Class(配置类)
        applicationContext.register(ObjectProviderDemo.class);

        //启动上下文
        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);

        lookupIfAvailable(applicationContext);

        lookupByStreamsOps(applicationContext);

        //显示的关闭Spring 应用上下文
        applicationContext.close();
    }

    private static void lookupByStreamsOps(AnnotationConfigApplicationContext applicationContext) {

        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
//        for(String str: objectProvider){
//            System.out.println(str);
//        }
        objectProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable(User::createUser);
        System.out.println("当前 User 对象："+ user);
    }

    @Bean
    @Primary
    public String helloWorld(){
        return "Hello,world";
    }

    @Bean
    public String message(){
        return "Message";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }
}
