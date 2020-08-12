package com.zt.dependency.bean.scope.scope;

import com.zt.dependency.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Random;

/**
 * 自定义 Scope {@link ThreadLocalScope} 示例
 *
 * @author Tommy
 * @date 2020/8/12 10:35 下午
 */
public class ThreadLocalScopeDemo {

    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public User user(){
        return createUser();
    }

    public static User createUser(){
        User user = new User();
        Random random = new Random();
        user.setId(random.nextInt());
        return user;
    }

    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class(配置类) -> Spring Bean
        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            //注册自定义scope
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME,new ThreadLocalScope());
        });
        //启动上下文
        applicationContext.refresh();
        scopedBeansByLookup(applicationContext);

        //关闭上下文
        applicationContext.close();
    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0;i<3;i++){
           Thread thread = new Thread(()->{
               User user = applicationContext.getBean("user",User.class);
               System.out.printf("[Thread id :%d] user  = %s %n" ,Thread.currentThread().getId(), user);
           });
           //启动线程
           thread.start();
           //强制线程执行完成
            try{
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {

    }


}
