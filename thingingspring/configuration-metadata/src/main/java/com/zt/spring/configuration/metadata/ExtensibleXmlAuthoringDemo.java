package com.zt.spring.configuration.metadata;

import com.zt.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Spring XML 元素扩展示例
 *
 * @Author: Tommy
 * @DATE: 2021/6/10
 */
public class ExtensibleXmlAuthoringDemo {

    public static void main(String[] args) {
        //创建ioc底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //创建XML资源的 BeanDefinitionReader
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //加载XML 资源
        reader.loadBeanDefinitions("META-INF/user-context.xml");
        //获取 User Bean 对象
        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }
}
