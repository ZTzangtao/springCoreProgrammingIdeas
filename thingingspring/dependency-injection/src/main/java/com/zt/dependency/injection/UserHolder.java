package com.zt.dependency.injection;

import com.zt.dependency.domain.User;
import lombok.Data;

/**
 * {@link User}的Holder对象
 *
 * @author Tommy
 * @date 2020/7/22 10:18 下午
 */
@Data
public class UserHolder {
    private User user;

    public UserHolder(User user) {
        this.user = user;
    }
    public UserHolder(){

    }
}
