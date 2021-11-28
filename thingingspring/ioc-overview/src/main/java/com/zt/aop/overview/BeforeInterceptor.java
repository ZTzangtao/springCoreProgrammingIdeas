package com.zt.aop.overview;

import java.lang.reflect.Method;

/**
 * 前置拦截器
 *
 * @Author Tommy
 * @Date 2021/11/28 10:53 AM
 * @Version 1.0
 */
public interface BeforeInterceptor {


    /**
     * 前置执行
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    Object before (Object proxy, Method method, Object[] args);
}
