package com.zt.dependency.injection;

import com.zt.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于 Java Annotation的依赖 Setter 方法注入示例
 *
 * @author Tommy
 * @date 2020/7/22 9:57 下午
 */
public class AnnotationDependencySetterInjectionDemo {

    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class(配置类)
        applicationContext.register(AnnotationDependencySetterInjectionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载 XML 资源，解析并且生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);


        //启动上下文
        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);


        //显示的关闭Spring 应用上下文
        applicationContext.close();

    }

    @Bean
    public UserHolder userHolder(User user){
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }

}
