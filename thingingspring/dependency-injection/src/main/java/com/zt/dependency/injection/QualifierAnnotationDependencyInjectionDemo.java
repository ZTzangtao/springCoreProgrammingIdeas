package com.zt.dependency.injection;

import com.zt.dependency.domain.User;
import com.zt.dependency.injection.annotation.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * {@link Qualifier} 注解依赖注入
 *
 * @author Tommy
 * @date 2020/7/27 8:50 下午
 */
@Configuration
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired //SuperUser -> primary = true
    private User user;

    @Autowired //指定Bean名称或者ID
    @Qualifier("user")
    private User namedUser;

    //整体应用上下文拥有4个 User类型的Bean
    @Autowired
    private Collection<User> allUsers;  // 2

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;

    @Autowired
    @UserGroup
    private Collection<User> groupUsers;

    @Bean
    @Qualifier  //进行逻辑分组
    public User user1(){
        return createUser(8);
    }

    @Bean
    @Qualifier //进行逻辑分组
    public User user2(){
        return createUser(7);
    }

    @Bean
    @UserGroup
    public User user3(){
        return createUser(2);
    }

    @Bean
    @UserGroup
    public User user4(){
        return createUser(3);
    }
    private static User createUser(int id){
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class(配置类)
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载 XML 资源，解析并且生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        //启动上下文
        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        System.out.println("demo.user = " + demo.user);
        System.out.println("demo.namedUser = " + demo.namedUser);
        System.out.println("demo.allUsers = " + demo.allUsers);
        System.out.println("demo.qualifierUsers = " + demo.qualifierUsers);
        System.out.println("demo.groupUsers = " + demo.groupUsers);

        //显示的关闭Spring 应用上下文
        applicationContext.close();

    }
}
