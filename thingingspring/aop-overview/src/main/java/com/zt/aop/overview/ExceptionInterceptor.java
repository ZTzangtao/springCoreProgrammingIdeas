package com.zt.aop.overview;

import java.lang.reflect.Method;

/**
 * 异常拦截
 *
 * @Author Tommy
 * @Date 2021/11/28 11:34 AM
 * @Version 1.0
 */
public interface ExceptionInterceptor {

    /**
     * @param proxy
     * @param method
     * @param args
     * @param throwable 异常信息
     */
    void intercept(Object proxy, Method method, Object[] args, Throwable throwable);

}
