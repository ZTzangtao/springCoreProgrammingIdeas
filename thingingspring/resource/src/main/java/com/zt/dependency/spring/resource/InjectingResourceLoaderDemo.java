package com.zt.dependency.spring.resource;

import com.zt.dependency.spring.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * 注入 {@link ResourceLoader}对象示例
 *
 * @Author: Tommy
 * @DATE: 2021/6/26
 *
 * @see ResourceLoader
 * @see Resource
 * @see Value
 * @see AnnotationConfigApplicationContext
 */
public class InjectingResourceLoaderDemo implements ResourceLoaderAware {

    //方法一
    private ResourceLoader resourceLoader;

    //方法二
    @Autowired
    private ResourceLoader autoWiredResourceLoader;

    //方法三
    @Autowired
    private AbstractApplicationContext applicationContext;

    @PostConstruct
    public void init(){
        System.out.println("resourceLoader == autoWiredResourceLoader : " + (resourceLoader == autoWiredResourceLoader));
        System.out.println("resourceLoader == applicationContext : " + (resourceLoader == applicationContext));

    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册当前类作为Configuration Class
        context.register(InjectingResourceLoaderDemo.class);
        //启动 Spring 应用上下文
        context.refresh();

        //关闭 Spring 应用上下文
        context.close();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
