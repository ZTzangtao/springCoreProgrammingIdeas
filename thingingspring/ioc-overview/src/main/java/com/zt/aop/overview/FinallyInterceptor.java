package com.zt.aop.overview;

import java.lang.reflect.Method;

/**
 *  最终执行后置拦截器
 *
 * @Author Tommy
 * @Date 2021/11/28 10:53 AM
 * @Version 1.0
 */
public interface FinallyInterceptor {


    /**
     * 最终执行
     *
     * @param proxy
     * @param method
     * @param args
     * @param returnResult 执行方法返回结果
     * @return
     */
    Object finalize(Object proxy, Method method, Object[] args, Object returnResult);
}
