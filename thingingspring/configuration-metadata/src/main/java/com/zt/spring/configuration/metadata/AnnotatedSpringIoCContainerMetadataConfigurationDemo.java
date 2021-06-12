package com.zt.spring.configuration.metadata;

import com.zt.dependency.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * 基于 Java注解 Spring IoC容器元信息配置示例
 *
 * @Author: Tommy
 * @DATE: 2021/6/7
 */
//将当前类作为Configuration Class
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
//java8 + @Repeatable 支持
@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
public class AnnotatedSpringIoCContainerMetadataConfigurationDemo {

    /**
     * user.name是java Properties 默认存在，当前用户：zt，而非配置文件中定义的“zt”
     *
     * @param id
     * @param name
     * @return
     */
    @Bean
    public User configuredUser(@Value("${user.id}") int id,@Value("${user.name}")String name ){
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册当前类作为Configuration Class
        context.register(AnnotatedSpringIoCContainerMetadataConfigurationDemo.class);

        //启动 Spring 应用上下文
        context.refresh();
        Map<String,User> usersMap = context.getBeansOfType(User.class);
        for (Map.Entry<String,User> entry : usersMap.entrySet()){
            System.out.printf("User Bean name : %s ,content : %s \n",entry.getKey(),entry.getValue());
        }
        //关闭 Spring 应用上下文
        context.close();
    }

}
