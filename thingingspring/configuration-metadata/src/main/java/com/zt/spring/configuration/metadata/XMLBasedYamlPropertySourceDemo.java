package com.zt.spring.configuration.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * 基于XML资源的 Yaml 外部化配置示例
 *
 * @Author: Tommy
 * @DATE: 2021/6/13
 */
public class XMLBasedYamlPropertySourceDemo {

    public static void main(String[] args) {
        //创建ioc底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //创建XML资源的 BeanDefinitionReader
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //加载XML 资源
        reader.loadBeanDefinitions("META-INF/yaml-property-source-context.xml");
        //获取 Map YAML 对象
        Map<String,Object> yamlMap = beanFactory.getBean("yamlMap",Map.class);
        System.out.println(yamlMap);
    }
}
