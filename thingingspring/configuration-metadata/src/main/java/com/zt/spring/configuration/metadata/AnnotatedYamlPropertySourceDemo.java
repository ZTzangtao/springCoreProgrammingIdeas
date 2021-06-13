package com.zt.spring.configuration.metadata;

import com.zt.dependency.domain.User;
import com.zt.dependency.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * 基于 java注解的 Yaml 外部化配置示例
 *
 * @Author: Tommy
 * @DATE: 2021/6/13
 */
@PropertySource(name="yamlProperties",value = "classpath:META-INF/user.yaml",
        factory = YamlPropertySourceFactory.class)
public class AnnotatedYamlPropertySourceDemo {

    @Bean
    public User configuredUser(@Value("${user.id}") int id, @Value("${user.name}")String name,
                               @Value("${user.city}")City city){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCity(city);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册当前类作为Configuration Class
        context.register(AnnotatedYamlPropertySourceDemo.class);
        //启动 Spring 应用上下文
        context.refresh();

        User user = context.getBean(User.class);
        System.out.println(user);
        //关闭 Spring 应用上下文
        context.close();
    }
}
