package com.zt.spring.configuration.metadata;

import com.zt.dependency.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * "user" 元素的 {@link org.springframework.beans.factory.xml.BeanDefinitionParser} 实现
 *
 * @Author: Tommy
 * @DATE: 2021/6/10
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        setPropertyValue("id",element,builder);
        setPropertyValue("name",element,builder);
        setPropertyValue("city",element,builder);
    }

    private void setPropertyValue(String attributeName, Element element, BeanDefinitionBuilder builder){
        String attributeValue = element.getAttribute(attributeName);
        if(StringUtils.hasText(attributeValue)){
//            BeanDefinition定义id属性 -> <property name="id" value="1"/>
            builder.addPropertyValue(attributeName,attributeValue);
        }
    }
}
