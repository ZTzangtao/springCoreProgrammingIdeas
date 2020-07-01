package com.zt.dependency.domain;

import com.zt.dependency.annotion.Super;
import lombok.Data;

@Data
@Super
public class SuperUser extends User {
    private String address;

}


