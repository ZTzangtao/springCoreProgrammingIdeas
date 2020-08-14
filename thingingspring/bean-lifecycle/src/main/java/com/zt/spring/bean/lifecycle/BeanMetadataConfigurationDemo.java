package com.zt.spring.bean.lifecycle;

import com.zt.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Bean 元信息配置示例
 * @author Tommy
 * @date 2020/8/14 10:38 下午
 */
public class BeanMetadataConfigurationDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "META-INF/user.properties";
        //基于classPath 读取资源
        Resource resource = new ClassPathResource(location);
        //指定字符编码
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(encodedResource);

        System.out.println("已加载的 BeanDefinition 数量 ：" + beanNumbers);

        User user = beanFactory.getBean("user", User.class);

        System.out.println(user);
    }

}
