package com.zt.aop.features.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect 配置类
 *
 * @Author Tommy
 * @Date 2021/12/1 9:30 PM
 * @Version 1.0
 */
@Aspect
public class AspectConfiguration {

    @Pointcut("execution(public * *(..))") // 匹配 Join Point
    private void anyPublicMethod(){ // 方法名即 Pointcut 名
        System.out.println("@Pointcut at any public method.");
    }

    @Before("anyPublicMethod()") // Join Point 拦截动作
    public void beforeAnyPublicMethod() {
        System.out.println("@Before any public method.");
    }

}
