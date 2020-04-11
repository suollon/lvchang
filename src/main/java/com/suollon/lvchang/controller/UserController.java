package com.suollon.lvchang.controller;

import com.suollon.lvchang.domain.entity.User;
import com.suollon.lvchang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public int add(@RequestBody User user) {
        return userService.insertSelective(user);
    }

    @PutMapping
    public int update(@RequestBody User user) {
        return userService.updateByPrimaryKeySelective(user);
    }

    @GetMapping
    public User get(@RequestParam("userId") Long userId) {
        return userService.selectByPrimaryKey(userId);
    }
}
