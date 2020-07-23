package com.zt.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于 API 实现依赖 Constructor 方法注入示例
 *
 * @author Tommy
 * @date 2020/7/22 9:57 下午
 */
public class ApiDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //生成 UserHolder 的BeanDefinition
        BeanDefinition userHolderBeanDefinition = createUserHolderBeanDefinition();
        //注册UserHolder 的BeanDefinition
        applicationContext.registerBeanDefinition("userHolder",userHolderBeanDefinition);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载 XML 资源，解析并且生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);


        //启动上下文
        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);


        //显示的关闭Spring 应用上下文
        applicationContext.close();

    }

    /**
     * {@link UserHolder}生成 {@link BeanDefinition}
     * @return
     */
    private static BeanDefinition createUserHolderBeanDefinition(){
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        definitionBuilder.addConstructorArgReference("supperUser");
        return  definitionBuilder.getBeanDefinition();
    }

//    @Bean
//    public UserHolder userHolder(User user){
//        UserHolder userHolder = new UserHolder();
//        userHolder.setUser(user);
//        return userHolder;
//    }

}
