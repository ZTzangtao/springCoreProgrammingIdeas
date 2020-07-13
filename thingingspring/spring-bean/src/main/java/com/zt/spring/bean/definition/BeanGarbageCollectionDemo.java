package com.zt.spring.bean.definition;

import com.zt.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 垃圾回收（GC）示例
 *
 * @author Tommy
 * @date 2020/7/13 10:44 下午
 */
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        //创建 BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class(配置类)
        applicationContext.register(BeanInitializationDemo.class);
        //启动spring应用上下文
        applicationContext.refresh();
        //关闭spring应用上下文
        applicationContext.close();
        System.out.println("Spring应用上下文 已关闭...");
        Thread.sleep(5000L);
        //强行触发gc
        System.gc();
        Thread.sleep(5000L);
    }

}
