package com.zt.spring.bean.definition;

import com.zt.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 显示别名
 *
 * @author Tommy
 * @date 2020/7/8 10:05 下午
 */
public class BeanAliasDemo {
    public static void main(String[] args) {
        //配置xml文件，
        //启动spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");
        //通过别名zt-user获取曾用名user 的bean
        User user =  beanFactory.getBean("user",User.class);


        //通过别名zt-user获取曾用名user 的bean
        User ztUser =  beanFactory.getBean("zt-user",User.class);

        System.out.println("zt-user 与 user相等"+ (user == ztUser));


    }

}
