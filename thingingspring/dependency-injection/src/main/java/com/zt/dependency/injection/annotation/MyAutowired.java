package com.zt.dependency.injection.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

/**
 * 自定义注解 （元标注 @Autowired）
 *
 * @author Tommy
 * @date 2020/7/31 10:08 下午
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD,  ElementType.FIELD,})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
public @interface MyAutowired {

    /**
     * Declares whether the annotated dependency is required.
     * <p>Defaults to {@code true}.
     */
    boolean required() default true;

}
