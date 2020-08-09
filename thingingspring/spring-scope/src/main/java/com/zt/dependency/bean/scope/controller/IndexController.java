package com.zt.dependency.bean.scope.controller;

import com.zt.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页 Spring Web MVC Controller
 *
 * @author Tommy
 * @date 2020/8/9 9:56 下午
 */
@Controller
public class IndexController {

    @Autowired
    private User user;

    @GetMapping("/index.html")
    public String index(Model model){
        model.addAttribute("user",user);
        return "index";
    }
}
