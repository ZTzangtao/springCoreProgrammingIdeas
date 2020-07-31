package com.zt.dependency.injection.annotation;

import java.lang.annotation.*;

/**
 * 自定义依赖注入注解
 *
 * @author Tommy
 * @date 2020/7/31 10:16 下午
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD,  ElementType.FIELD,})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {
}
