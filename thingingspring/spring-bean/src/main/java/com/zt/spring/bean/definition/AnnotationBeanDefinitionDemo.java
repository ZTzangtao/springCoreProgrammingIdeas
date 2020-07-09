package com.zt.spring.bean.definition;

import com.zt.dependency.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * 注解 BeanDefinition 示例
 * @author Tommy
 * @date 2020/7/9 9:47 下午
 */
//3、通过@Import来进行导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class(配置类)
        applicationContext.register(AnnotationBeanDefinitionDemo.class);

        //通过BeanDefinition注册API实现
        //1.命名Bean的注册方式
        registerUserBeanDefinition(applicationContext,"mercy-user");
        //2.非命名
        registerUserBeanDefinition(applicationContext);
        //启动上下文
        applicationContext.refresh();

        //按照类型依赖查找
        System.out.println("config类型的的所有Beans"+applicationContext.getBeansOfType(Config.class));
        System.out.println("User类型的的所有Beans"+applicationContext.getBeansOfType(User.class));
        //显示的关闭Spring 应用上下文
        applicationContext.close();
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry,String beanName){
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id",2)
                .addPropertyValue("name","zt");
        //判断如果beanName参数存在
        if (StringUtils.hasText(beanName)){
            //注册BeanDefinition
            registry.registerBeanDefinition(beanName,beanDefinitionBuilder.getBeanDefinition());
        }else {
            //非命名方式
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(),registry);
        }
    }
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry){
        registerUserBeanDefinition(registry,null);
    }

    //2、通过@Component 方式
    @Component //定义当前类作为Spring Bean（组件）
    public static class Config{
        //1、 通过@Bean的方式定义
        /**
         * 通过java注解方式 定义了一个bean
         */
        @Bean(name={"user","zt-user"})
        public User user(){
            User user = new User();
            user.setId(1);
            user.setName("zt");
            return user;
        }
    }

}
