package com.zt.dependency.spring.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Java 泛型 API 示例
 *
 * @Author: Tommy
 * @DATE: 2021/10/2
 */
public class GenericAPIDemo {

    public static void main(String[] args) {

        // 原生类型 primitive types : int long short double float byte char boolean

        // 数组类型 array types :  int[], Object[]
        Class objectArrayClass = Object[].class;

        // 原始类型 raw types : java.lang.String
        Class rawClass = String.class;

        // 泛型参数类型 parameterized type
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();

        // parameterizedType.getRawType() = java.util.AbstractList

        // 泛型类型变量 Type Variable :


        System.out.println(parameterizedType.toString());

        // <E>
        Type[] typeVariables = parameterizedType.getActualTypeArguments();
        // Type -> TypeVariable
        Stream.of(typeVariables).map(TypeVariable.class::cast).forEach(System.out::println);
    }

}
