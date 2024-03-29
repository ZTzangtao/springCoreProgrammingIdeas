package com.zt.aop.features;

import com.zt.aop.features.aspect.AspectConfiguration;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Tommy
 * @Date 2021/11/29 9:43 PM
 * @Version 1.0
 */
// 声明为 Aspect 切面
public class AspectJAnnotationUsingAPIDemo {

    public static void main(String[] args) {

        // 通过创建一个 HashMap 缓存，作为被代理对象
        Map<String, Object> cache = new HashMap<>();

        // 创建 Proxy工厂（AspectJ）
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache);

        // 增加 Aspect 配置类
        proxyFactory.addAspect(AspectConfiguration.class);

        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                if("put".equals(method.getName()) && args.length == 2){
                    System.out.printf("[MethodBeforeAdvice] 当前存放是 Key : %s , Value : %s \n", args[0], args[1]);
                }
            }
        });

        // 添加 AfterReturningAdvice
        proxyFactory.addAdvice(new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                if("put".equals(method.getName()) && args.length == 2){
                    System.out.printf("[AfterReturningAdvice] 当前存放是 Key : %s , 新存放的 Value : %s , 之前关联的 Value : %s\n ",
                            args[0],    // key
                            args[1],    // new Value
                            returnValue // old value
                    );
                }
            }
        });
//        cache.put("1", "A");
        // 通过代理对象存储
        Map<String, Object> proxy = proxyFactory.getProxy();
        proxy.put("1", "A");
        System.out.println(cache.get("1"));
    }

}
