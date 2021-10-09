package com.zt.dependency.spring.generic;

import org.springframework.core.ResolvableType;
/**
 * {@link ResolvableType} Demo
 *
 * @Author: Tommy
 * @DATE: 2021/10/9
 * @see ResolvableType
 * @since
 */
public class ResolvableTypeDemo {

    public static void main(String[] args) {
        // 工厂创建
        // StringList <- ArrayList <- AbstractList <- List <- Collection
        ResolvableType resolvableType = ResolvableType.forClass(GenericTypeResolverDemo.StringList.class);

        resolvableType.getSuperType(); // ArrayList
        resolvableType.getSuperType().getSuperType(); // AbstractList

        // 获取 Raw Type
        System.out.println(resolvableType.asCollection().resolve());
        // 获取泛型参数类型
        System.out.println(resolvableType.asCollection().resolveGeneric(0));
    }

}
