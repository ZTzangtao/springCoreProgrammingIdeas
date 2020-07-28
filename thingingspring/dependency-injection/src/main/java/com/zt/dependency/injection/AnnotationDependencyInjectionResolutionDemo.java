package com.zt.dependency.injection;

import com.zt.dependency.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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

    @Autowired
    private Optional<User> optionalUser;

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


//        demo.userObjectProvider.forEach(System.out::println);

        //显示的关闭Spring 应用上下文
        applicationContext.close();

    }
}
