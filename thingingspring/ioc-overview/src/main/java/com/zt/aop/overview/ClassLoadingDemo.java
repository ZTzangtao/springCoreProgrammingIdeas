package com.zt.aop.overview;

/**
 * 类加载示例
 *
 * @Author Tommy
 * @Date 2021/11/23 10:43 PM
 * @Version 1.0
 */
public class ClassLoadingDemo {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);

        ClassLoader parentClassLoader = classLoader;
        while (true) {
             parentClassLoader = parentClassLoader.getParent();
             //Bootstrap ClassLoader
            if(parentClassLoader == null) {
                break;
            }
            System.out.println(parentClassLoader);
        }
    }


}
