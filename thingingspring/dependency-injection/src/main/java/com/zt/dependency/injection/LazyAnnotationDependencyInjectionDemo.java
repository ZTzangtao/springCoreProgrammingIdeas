package com.zt.dependency.injection;

import com.zt.dependency.domain.User;
import com.zt.dependency.injection.annotation.UserGroup;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.rmi.PortableRemoteObject;
import java.util.Collection;
import java.util.Set;

/**
 * {@link ObjectProvider} 实现延迟依赖注入
 *
 * @author Tommy
 * @date 2020/7/27 8:50 下午
 */
@Configuration
public class LazyAnnotationDependencyInjectionDemo {

    //实时注入
    @Autowired
    private User user;

    //延迟注入
    @Autowired
    private ObjectProvider<User> userObjectProvider;

    @Autowired
    private ObjectFactory<Set<User>> userObjectFactory;


    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class(配置类)
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载 XML 资源，解析并且生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        //启动上下文
        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        System.out.println("demo.user =" + demo.user);

        //继承了ObjectFactory
        System.out.println("demo.userObjectProvider =" + demo.userObjectProvider.getObject());

        System.out.println("demo.userObjectFactory =" + demo.userObjectFactory.getObject());

//        demo.userObjectProvider.forEach(System.out::println);

        //显示的关闭Spring 应用上下文
        applicationContext.close();

    }
}
