package com.zt.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link NoUniqueBeanDefinitionException} 示例代码
 *
 * @author Tommy
 * @date 2020/7/19 9:46 下午
 */
public class NoUniqueBeanDefinitionExceptionDemo {

    public static void main(String[] args) {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册ObjectProviderDemo Class(配置类)
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        //启动上下文
        applicationContext.refresh();
        try{
            //由于Spring应用上下文存在两个String类型的bean，通过单一类型查找会抛出异常
            applicationContext.getBean(String.class);
        }catch (NoUniqueBeanDefinitionException e){
            System.err.printf("Spring应用上下文存在 %d 个 %s 类型的Bean,具体原因：%s%n ",
                    e.getNumberOfBeansFound(),
                    String.class.getName(),
                    e.getMessage() );
        }




        //显示的关闭Spring 应用上下文
        applicationContext.close();
    }

    @Bean
    public String bean1(){
        return "bean1";
    }

    @Bean
    public String bean2(){
        return "bean2";
    }

    @Bean
    public String bean3(){
        return "bean3";
    }

}
