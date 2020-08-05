package com.zt.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * 外部化配置作为依赖来源示例
 *
 * @author Tommy
 * @date 2020/8/5 9:32 下午
 */
@Configuration
@PropertySource(value = "META-INF/default.properties",encoding = "UTF-8")
public class ExternalConfigurationDependencySourceDemo {

    @Value("${user.id:-1}")
    private Long id;

    @Value("${usr.name:zt}")
    private String name;

    @Value("${user.resource:classpath://default.properties}")
    private Resource resource;


    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class(配置类) -> Spring Bean
        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);

        //启动上下文
        applicationContext.refresh();

        ExternalConfigurationDependencySourceDemo dependencySourceDemo = applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);

        System.out.println("user.id:" + dependencySourceDemo.id);
        System.out.println("usr.name:" + dependencySourceDemo.name);

        System.out.println("user.resource:" + dependencySourceDemo.resource);


        //显示的关闭Spring 应用上下文
        applicationContext.close();
    }

}
