package com.zt.dependency.repository;

import com.zt.dependency.domain.User;
import lombok.Data;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * 用户信息仓库
 */
@Data
public class UserRepository {
    //自定义 bean
    private Collection<User> users;
    //内建 非bean对象
    private BeanFactory beanFactory;

    private ObjectFactory<ApplicationContext> objectFactory;
}
