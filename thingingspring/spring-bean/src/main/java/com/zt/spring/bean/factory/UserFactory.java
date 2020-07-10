package com.zt.spring.bean.factory;

import com.zt.dependency.domain.User;

/**
 * @author Tommy
 * @date 2020/7/10 9:36 下午
 */
public interface UserFactory {
    default User createUser(){
      return  User.createUser();
    }
}
