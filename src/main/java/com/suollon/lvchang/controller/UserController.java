package com.suollon.lvchang.controller;

import com.suollon.lvchang.dao.UserDAO;
import com.suollon.lvchang.domain.entity.User;
import com.suollon.lvchang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * restful API
 * @author wangwl
 * @date 2020/4/11 23:46
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @PostMapping
    public void insertSelective(@RequestBody User user) {
        userService.insertSelective(user);
    }

    @PutMapping
    public String updateByPrimaryKeySelective(@RequestBody User user) {
        if (user.getUserId() == null) {
            return "userId不能为空";
        }
        userService.updateByPrimaryKeySelective(user);
        return "SUCCESS";
    }

    @GetMapping
    public User selectByPrimaryKey(Long userId) {
        return userService.selectByPrimaryKey(userId);
    }

}
