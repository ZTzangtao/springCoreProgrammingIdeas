package com.zt.dependency.bean.scope.web;

import com.zt.dependency.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Tommy
 * @date 2020/8/9 10:01 下午
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {

    @Bean
    @RequestScope
    public User user(){
        User user = new User();
        user.setId(3);
        user.setName("zt");
        return user;
    }

}
