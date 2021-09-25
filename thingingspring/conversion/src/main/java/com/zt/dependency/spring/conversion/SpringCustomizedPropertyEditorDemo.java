package com.zt.dependency.spring.conversion;

import com.zt.dependency.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyEditor;

/**
 * Spring 自定义 {@link PropertyEditor} 示例
 *
 * @Author: Tommy
 * @DATE: 2021/8/19
 *
 * @see PropertyEditor
 */
public class SpringCustomizedPropertyEditorDemo {

    public static void main(String[] args) {
        // 创建并且启动 BeanFactory 容器
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/property-editors-context.xml");

        // AbstractApplicationContext -> "conversionService" ConversionService Bean
        // -> ConfigurableBeanFactory#setConversionService(ConversionService)
        // -> BeanFactory -> AbstractBeanFactory#getConversionService -> BeanDefinition -> BeanWrapper
        // BeanDefinition -> BeanWrapper -> 属性转换（数据来源：PropertyValues）
        // setPropertyValues(PropertyValues) -> TypeConverter#converIfNecessnary
        // TypeConverterDelegate#converIfNecessnary -> PropertyEditor or ConversionService

        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

        //关闭或者停止
        applicationContext.close();
    }

}
