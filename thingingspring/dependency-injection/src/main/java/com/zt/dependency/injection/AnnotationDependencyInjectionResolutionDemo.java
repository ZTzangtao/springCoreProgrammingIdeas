package com.zt.dependency.injection;

import com.zt.dependency.domain.User;
import com.zt.dependency.injection.annotation.InjectedUser;
import com.zt.dependency.injection.annotation.MyAutowired;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.*;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * 注解驱动的依赖注入
 *
 * @author Tommy
 * @date 2020/7/27 8:50 下午
 */
@Configuration
public class AnnotationDependencyInjectionResolutionDemo {

    // 查找依赖(处理)
    //DependencyDescriptor ->
    // 必须（required=true）
    // 实时注入(eager = true)
    // 通过类型（User.class）
    // 字段名称（"user"）
    //是否首要（primary = true）
    @Autowired
    private User user;

    //集合类型注入
    @Autowired
    private Map<String,User> users;

    @MyAutowired
    private Optional<User> optionalUser;

    @Inject
    private User injectedUser;

    @InjectedUser
    private User myInjectedUser;

//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        //替换原有注解处理，使用新注解 @InjectedUser
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(Arrays.asList(Autowired.class,Inject.class,InjectedUser.class));
//        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//        return beanPostProcessor;
//
//    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return beanPostProcessor;

    }

    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class(配置类)
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载 XML 资源，解析并且生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        //启动上下文
        applicationContext.refresh();

        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        System.out.println("demo.user =" + demo.user);
        System.out.println("demo.users =" + demo.users);
        System.out.println("demo.optionalUser =" + demo.optionalUser);
        System.out.println("demo.injectedUser =" + demo.injectedUser);
        System.out.println("demo.myInjectedUser =" + demo.myInjectedUser);


//        demo.userObjectProvider.forEach(System.out::println);

        //显示的关闭Spring 应用上下文
        applicationContext.close();

    }
}
