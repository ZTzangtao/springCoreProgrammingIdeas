package com.zt.spring.bean.definition;

import com.zt.dependency.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 构建示例
 *
 * @author Tommy
 * @date 2020/7/7 10:03 下午
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {
        //1.通过BeanDefinitionBuilder 构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        //通过属性设置
        beanDefinitionBuilder
                .addPropertyValue("id",1)
                .addPropertyValue("name","zt");

        //设置初始化方法 (failed!!) todo
         beanDefinitionBuilder.setInitMethodName("ztTest");


        //获取BeanDefinition实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        System.out.println(beanDefinition.getInitMethodName());


        //BeanDefinition并非Bean终态，可以自定义修改

        //2.通过 AbstractBeanDefinition 以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        //设置 Bean的类型
        genericBeanDefinition.setBeanClass(User.class);
        //通过MutablePropertyValues 批量操作属性
        MutablePropertyValues propertyValues = new MutablePropertyValues();
//        propertyValues.addPropertyValue("id",1);
//        propertyValues.addPropertyValue("name","zt");
        propertyValues
                .add("id",1)
                .add("name","zt");
        genericBeanDefinition.setPropertyValues(propertyValues);
    }
}
