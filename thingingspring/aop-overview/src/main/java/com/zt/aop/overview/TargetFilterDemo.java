package com.zt.aop.overview;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * AOP 目标过滤示例
 *
 * @Author Tommy
 * @Date 2021/11/27 9:34 PM
 * @Version 1.0
 */
public class TargetFilterDemo {


    public static void main(String[] args) throws ClassNotFoundException {
        String targetClassName = "com.zt.aop.EchoService";

        // 获取当前线程 ClassLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // 获取目标类
        Class<?> targetClass = classLoader.loadClass(targetClassName);
        // 方法定义： String echo(String message)

        // Spring 反射工具类
        Method targetMethod = ReflectionUtils.findMethod(targetClass,"echo",String.class);

        System.out.println(targetMethod);


        // 查找方法 throws 类型为 NullPointerEception
        ReflectionUtils.doWithMethods(targetClass, new ReflectionUtils.MethodCallback() {
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                System.out.println("仅抛出 NullPointerEception 方法为：" + method);
            }
        }, new ReflectionUtils.MethodFilter() {
            @Override
            public boolean matches(Method method) {

                Class[] parameterTypes = method.getParameterTypes();

                Class[] exceptionTypes = method.getExceptionTypes();

                return parameterTypes.length == 1
                        && String.class.equals(parameterTypes[0])
                        && exceptionTypes.length == 1
                        && NullPointerException.class.equals(exceptionTypes[0]);
            }
        });

    }


}
