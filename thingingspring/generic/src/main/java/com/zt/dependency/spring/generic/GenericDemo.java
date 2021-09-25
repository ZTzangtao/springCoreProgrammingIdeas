package com.zt.dependency.spring.generic;


import java.util.ArrayList;
import java.util.Collection;

/**
 * Java 泛型示例
 *
 * @Author: Tommy
 * @DATE: 2021/9/25
 */
public class GenericDemo {

    public static void main(String[] args) {
        // java7 Diamond 语法
        Collection<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        //编译时错误
        //list.add(1);

        // 泛型擦写
        Collection temp = list;
        // 编译通过
        temp.add(1);

        // c元素变成了Object类型
        temp.forEach( c-> {
            if("1".equals(c.toString())){
                System.out.println("str");
            }
        });


        System.out.println(list);
    }

}
