package com.zt.aop.features;

import com.zt.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.zt.aop.features.pointcut.EchoServiceEchoMethodPointcut;
import com.zt.aop.features.pointcut.EchoServicePointcut;
import com.zt.aop.overview.DefaultEchoService;
import com.zt.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @Author: Tommy
 * @DATE: 2021/12/11
 */
public class RawPointcutAPIDemo {

    public static void main(String[] args) {

        EchoServiceEchoMethodPointcut pointcut = EchoServiceEchoMethodPointcut.INSTANCE;

//        EchoServicePointcut pointcut = new EchoServicePointcut("echo", EchoService.class);

        // 将 Pointcut 适配成 Advisor

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new EchoServiceMethodInterceptor());

        DefaultEchoService defaultEchoService = new DefaultEchoService();
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);

        // 添加 Advisor
        proxyFactory.addAdvisor(advisor);

        // 获取代理对象
        EchoService echoService = (EchoService) proxyFactory.getProxy();
        System.out.println(echoService.echo("Hello,World"));

    }

}
