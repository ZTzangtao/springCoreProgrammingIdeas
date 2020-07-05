package com.zt.dependency.container;

import com.zt.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author zangtao
 *
 * BeanFactoryAsIoc
 */
public class BeanFactoryAsIocContainerDemo {

    public static void main(String[] args) {
        //创建 BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //XML配置文件 ClassPath路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载配置
        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
        System.out.println("beanDefinitionsCount："+beanDefinitionsCount);

        //依赖查找
        lookupByCollectionType(beanFactory);
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有集合对象："+ users);
        }
    }

}
