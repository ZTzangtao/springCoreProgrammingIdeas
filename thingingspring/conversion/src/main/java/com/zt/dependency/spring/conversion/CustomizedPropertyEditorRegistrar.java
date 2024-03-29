package com.zt.dependency.spring.conversion;

import com.zt.dependency.domain.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.stereotype.Component;

/**
 * 自定义 {@link PropertyEditorRegistrar} 实现
 *
 * @Author: Tommy
 * @DATE: 2021/8/19
 *
 * @see PropertyEditorRegistrar
 */
//@Component // 3. 将其声明为 Spring Bean
public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        // 1. 通用类型转换
        // 2. Java Bean 属性类型转换

        registry.registerCustomEditor(User.class,"context", new StringToPropertiesPropertyEditor());

    }
}
