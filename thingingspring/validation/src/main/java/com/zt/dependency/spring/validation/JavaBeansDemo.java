package com.zt.dependency.spring.validation;

import com.zt.dependency.domain.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * JavaBeans 示例
 *
 * @Author: Tommy
 * @DATE: 2021/8/8
 */
public class JavaBeansDemo {

    public static void main(String[] args) throws IntrospectionException {
        // stopClass 排除（截止） 类
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);
        // 属性描述符 PropertyDescriptor

        // 所有 Java 类均继承 java.lang.Object
        // class 属性来自于 Object#getClass() 方法
        Stream.of( beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {
                    // Getter 方法
//                    propertyDescriptor.getReadMethod();
                    // Setter 方法
//                    propertyDescriptor.getWriteMethod();
                    System.out.println(propertyDescriptor);
                });

        // 输出 User 定义的方法 MethodDescriptor
        Stream.of( beanInfo.getMethodDescriptors())
                .forEach(System.out::println);

    }

}
