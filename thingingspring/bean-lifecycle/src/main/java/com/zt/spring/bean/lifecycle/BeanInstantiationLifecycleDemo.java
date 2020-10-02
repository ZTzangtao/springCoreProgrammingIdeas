package com.zt.spring.bean.lifecycle;

import com.zt.dependency.domain.SuperUser;
import com.zt.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

/**
 * Bean实例化生命周期示例
 *
 * @author Tommy
 * @date 2020/10/2 10:37 上午
 */
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加PostBeanProcessor 实例
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());


        //基于XML 资源BeanDefinitionReader实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String location = "META-INF/dependency-lookup-context.xml";
        //基于classPath XML 资源
        Resource resource = new ClassPathResource(location);
        //指定字符编码
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(encodedResource);

        System.out.println("已加载的 BeanDefinition 数量 ：" + beanNumbers);

//        User user = beanFactory.getBean("user", User.class);
//
//        System.out.println(user);

        User superUser = beanFactory.getBean("supperUser", User.class);

        System.out.println(superUser);


    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("supperUser",beanName) && SuperUser.class.equals(beanClass)){
                return new SuperUser();
            }
            return null;
        }
    }

}

