package com.suollon.lvchang.controller;

import com.suollon.lvchang.dao.UserDAO;
import com.suollon.lvchang.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangwl
 * @date 2020/4/11 20:57
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/hello")
    public String hello(String name) {
        return "Hello " + name + " !";
    }

    @GetMapping("/user")
    public User getUser(Long userId) {
        User user = userDAO.selectByPrimaryKey(userId);
        return user;
    }

}
