package com.zt.spring.configuration.metadata;

import com.zt.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

/**
 * {@link org.springframework.beans.factory.support.PropertiesBeanDefinitionReader} 示例
 *
 * @Author Tommy
 * @Date 2021/2/7 9:59 PM
 * @Version 1.0
 */
public class PropertiesBeanDefinitionReaderDemo {

    /**
     * Properties装在spring bean 不是特别流行
     *
     * @param args
     */
    public static void main(String[] args) {
        //创建ioc底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //创建面向Properties资源的BeanDefinitionReader示例
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        //Properties资源加载默认通过ISO-8859-1，实际存储UTF-8
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        //通过指定的ClassPath获取Resource对象
        Resource resource = resourceLoader.getResource("classpath:/META-INF/user-bean-definitions.properties");
        //转换成带有字符编码 EncodedResource对象
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        int beanDefinitionsCount = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println(String.format("已加载 %d 个BeanDefinition\n",beanDefinitionsCount));
        User user = beanFactory.getBean(User.class);
        System.out.println(user);


    }

}
