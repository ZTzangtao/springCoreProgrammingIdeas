package com.zt.aop.features.aspect;

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

}
