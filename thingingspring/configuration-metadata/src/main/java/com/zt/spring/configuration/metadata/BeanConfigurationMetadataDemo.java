package com.zt.spring.configuration.metadata;

import com.zt.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * bean 配置元信息示例
 *
 * @Author Tommy
 * @Date 2021/1/27 10:20 PM
 * @Version 1.0
 */
public class BeanConfigurationMetadataDemo {
    public static void main(String[] args) {

        //BeanDefinition的声明
//        BeanDefinition beanDefinition = new GenericBeanDefinition();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);

        beanDefinitionBuilder.addPropertyValue("name","Tommy");

        AbstractBeanDefinition abstractBean = beanDefinitionBuilder.getBeanDefinition();
        //附加属性 不影响 实例化，初始化，
        abstractBean.setAttribute("name","zt");
        //当前abstractBean 来自何方，起到辅助作用
        abstractBean.setSource(BeanConfigurationMetadataDemo.class);


        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals("user",beanName) && User.class.equals(bean.getClass())){
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
                    //通过source来判断
                    if(BeanConfigurationMetadataDemo.class.equals(bd.getSource())){
                        //上下文属性（存储）对象
                        String name = (String) bd.getAttribute("name");
                        User user = (User) bean;
                        user.setName(name);
                    }

                }

                return bean;
            }
        });


        beanFactory.registerBeanDefinition("user",abstractBean);

        User user = beanFactory.getBean("user",User.class);


        System.out.println(user);
    }
}
