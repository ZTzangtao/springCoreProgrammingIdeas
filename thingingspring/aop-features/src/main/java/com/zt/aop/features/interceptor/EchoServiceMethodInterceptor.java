package com.zt.aop.features.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @Author: Tommy
 * @DATE: 2021/12/4
 */
public class EchoServiceMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        System.out.println("拦截 EchoService 的方法：" + method);
        return invocation.proceed();
//        return null;
    }
}
