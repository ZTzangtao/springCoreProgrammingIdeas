package com.zt.spring.configuration.metadata;

import com.zt.dependency.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 外部化配置示例
 *
 * @Author: Tommy
 * @DATE: 2021/6/12
 */
@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
public class PropertySourceDemo {

    @Bean
    public User user(@Value("${user.id}") int id, @Value("${user.name}")String name ){
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //扩展 Environment 中的PropertySources
        //添加PropertySource操作必须在 refresh方法之前完成
        Map<String,Object> propertiesSource = new HashMap<>();
        propertiesSource.put("user.name","Tommy哥");
        org.springframework.core.env.PropertySource propertySource = new MapPropertySource("first-property-source",propertiesSource);
        context.getEnvironment().getPropertySources().addFirst(propertySource);


        //注册当前类作为Configuration Class
        context.register(PropertySourceDemo.class);

        //启动 Spring 应用上下文
        context.refresh();
        Map<String, User> usersMap = context.getBeansOfType(User.class);
        for (Map.Entry<String,User> entry : usersMap.entrySet()){
            System.out.printf("User Bean name : %s ,content : %s \n",entry.getKey(),entry.getValue());
        }
        System.out.println(context.getEnvironment().getPropertySources());
        //关闭 Spring 应用上下文
        context.close();
    }
}
