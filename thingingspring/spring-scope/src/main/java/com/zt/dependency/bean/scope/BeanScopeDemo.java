package com.zt.dependency.bean.scope;

import com.zt.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;
import java.util.Random;

/**
 * Bean的作用域示例
 *
 * @author Tommy
 * @date 2020/8/2 9:28 下午
 */
public class BeanScopeDemo implements DisposableBean {

    @Bean
    //默认scope就是singleton
    public static User singletonUser(){
         return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser(){
       return createUser();
    }

    public static User createUser(){
        User user = new User();
        Random random = new Random();
        user.setId(random.nextInt());
        return user;
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;
    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private Map<String,User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory; //Resolvable Dependency

    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class(配置类) -> Spring Bean
        applicationContext.register(BeanScopeDemo.class);

//        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
//            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
//                @Override
//                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//                    System.out.printf("%s Bean名称：%s 在初始化回调...%n",bean.getClass().getName(),beanName);
//                    return bean;
//                }
//            });
//        });
        //启动上下文
        applicationContext.refresh();

        //结论一：
        //Singleton Bean 无论依赖查找还是依赖注入，均为同一个对象
        //Prototype Bean 无论依赖查找还是依赖注入，均生成新的对象

        //结论二：
        //如果依赖注入集合类型的对象，Singleton Bean 和Prototype Bean均会存在一个
        //Prototype Bean 有别于其他地方的依赖注入 Prototype Bean

        //结论三：
        //无论是Singleton 还是Prototype Bean 均会执行初始化方法回调
        //不过只有Singleton可以执行销毁方法
        scopedBeansByLookup(applicationContext);

        scopedBeansByInjection(applicationContext);

        //显示的关闭Spring 应用上下文
        applicationContext.close();
    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0;i<3;i++){
            User singletonUser = applicationContext.getBean("singletonUser",User.class);
            System.out.println("singletonUser" + singletonUser);

            User prototypeUser = applicationContext.getBean("prototypeUser",User.class);
            System.out.println("prototypeUser" + prototypeUser);
        }
    }

    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo beanScopeDemo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("singletonUser"+beanScopeDemo.singletonUser);
        System.out.println("singletonUser1"+beanScopeDemo.singletonUser1);

        System.out.println("prototypeUser"+beanScopeDemo.prototypeUser);
        System.out.println("prototypeUser1"+beanScopeDemo.prototypeUser1);
        System.out.println("prototypeUser2"+beanScopeDemo.prototypeUser2);

        System.out.println("users"+beanScopeDemo.users);

    }


    @Override
    public void destroy() throws Exception {
        System.out.println("当前 BeanScopeDemo Bean 正在销毁中...");

        this.prototypeUser.destroyBean();
        this.prototypeUser1.destroyBean();
        this.prototypeUser2.destroyBean();
        //获取 BeanDefinition
        for(Map.Entry<String,User> entry : this.users.entrySet()){
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            //如果当前bean是prototype
            if(beanDefinition.isPrototype()){
                User user = entry.getValue();
                user.destroyBean();
            }
        }
        System.out.println("当前 BeanScopeDemo Bean 销毁完成");

    }
}
