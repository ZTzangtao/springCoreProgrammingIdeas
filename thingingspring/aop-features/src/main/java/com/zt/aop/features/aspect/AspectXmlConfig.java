package com.zt.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;

import java.util.Random;

/**
 * Aspect 配置类
 *
 * @Author Tommy
 * @Date 2021/12/1 9:30 PM
 * @Version 1.0
 */
public class AspectXmlConfig {

    private void anyPublicMethod(){ // 方法名即 Pointcut 名
        System.out.println("@Pointcut at any public method.");
    }

    public void beforeAnyPublicMethod() {
        System.out.println("@Before any public method.");
    }

    public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
        Random random = new Random();

        if(random.nextBoolean()){
            throw new RuntimeException("For Purpose from XML configuration.");
        }

        System.out.println("@Around any public method." + pjp.getSignature());

        return pjp.proceed();
    }

    public void finalizeAnyPublicMethod() {
        System.out.println("@After any public method.");
    }

    public void afterAnyPublicMethod() {
        System.out.println("@AfterReturning any public method.");
    }

    public void afterThrowingAnyPublicMethod() {
        System.out.println("@AfterThrowing any public method.");
    }

}
