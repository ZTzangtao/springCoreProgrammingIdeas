package com.zt.aop.features;

import com.zt.aop.overview.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于 XML 配置 Pointcut 示例
 *
 * @Author Tommy
 * @Date 2021/11/29 9:43 PM
 * @Version 1.0
 */
public class AspectJSchemaBasedPointcutDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-context.xml");

        context.refresh();

        EchoService echoService = context.getBean("echoService", EchoService.class);

        System.out.println(echoService.echo("Hello,World"));

        context.close();
    }


}
