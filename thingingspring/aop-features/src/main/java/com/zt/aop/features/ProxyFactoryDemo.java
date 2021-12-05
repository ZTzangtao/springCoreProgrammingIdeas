package com.zt.aop.features;

import com.zt.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.zt.aop.overview.DefaultEchoService;
import com.zt.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @Author: Tommy
 * @DATE: 2021/12/5
 */
public class ProxyFactoryDemo {

    public static void main(String[] args) {
        DefaultEchoService defaultEchoService = new DefaultEchoService();
        // 注入目标对象 （被代理）
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
        // 添加 Advice 实现MethodInterceptor < Interceptor < Advice
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        // 获取代理对象
        EchoService echoService = (EchoService) proxyFactory.getProxy();
        System.out.println(echoService.echo("Hello,World"));

    }

}
