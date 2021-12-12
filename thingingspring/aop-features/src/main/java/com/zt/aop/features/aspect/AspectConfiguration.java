package com.zt.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

import java.util.Random;

/**
 * Aspect 配置类
 *
 * @Author Tommy
 * @Date 2021/12/1 9:30 PM
 * @Version 1.0
 */
@Aspect
@Order
public class AspectConfiguration {

    @Pointcut("execution(public * *(..))") // 匹配 Join Point
    private void anyPublicMethod(){ // 方法名即 Pointcut 名
        System.out.println("@Pointcut at any public method.");
    }

    @Around("anyPublicMethod()")  // Join Point 拦截动作
    public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around any public method.");
        return pjp.proceed();
    }

    @Before("anyPublicMethod()") // Join Point 拦截动作
    public void beforeAnyPublicMethod() throws Throwable {
        Random random = new Random();

        if(random.nextBoolean()){
            throw new RuntimeException("For Purpose.");
        }
        System.out.println("@Before any public method.");
    }

    @After("anyPublicMethod()")
    public void finalizeAnyPublicMethod() {
        System.out.println("@After any public method.");
    }

    @AfterReturning("anyPublicMethod()")
    public void afterAnyPublicMethod() {
        System.out.println("@AfterReturning any public method.");
    }

    @AfterThrowing("anyPublicMethod()")
    public void afterThrowingAnyPublicMethod() {
        System.out.println("@AfterThrowing any public method.");
    }

}
