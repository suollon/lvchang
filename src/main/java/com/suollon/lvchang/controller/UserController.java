package com.suollon.lvchang.controller;

import com.suollon.lvchang.dao.UserDAO;
import com.suollon.lvchang.domain.entity.User;
import com.suollon.lvchang.domain.vo.UserAddVO;
import com.suollon.lvchang.domain.vo.UserUpdateVO;
import com.suollon.lvchang.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * restful API
 * @author wangwl
 * @date 2020/4/11 23:46
 */
@RestController
@RequestMapping("/user")
@Api(description = "用户操作类")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @PostMapping
    @ApiOperation("新增用户信息")
    public void insertSelective(@RequestBody @Validated UserAddVO userAddVO) {
        User user = User.build()
                .userName(userAddVO.getUserName())
                .address(userAddVO.getAddress())
                .phone(userAddVO.getPhone())
                .createTime(new Date())
                .updateTime(new Date());
        userService.insertSelective(user);
    }

    @PutMapping
    @ApiOperation("更新用户信息")
    public String updateByPrimaryKeySelective(@RequestBody @Validated UserUpdateVO userUpdateVO) {
        User user = User.build()
                .userId(userUpdateVO.getUserId())
                .userName(userUpdateVO.getUserName())
                .address(userUpdateVO.getAddress())
                .phone(userUpdateVO.getPhone())
                .updateTime(new Date());
        userService.updateByPrimaryKeySelective(user);
        return "SUCCESS";
    }

    @GetMapping
    @ApiOperation("按用户ID查询用户信息")
    public User selectByPrimaryKey(@RequestParam("userId") @Validated @NotNull(message = "用户ID不能为空")
                                       @ApiParam("用户ID") Long userId) {
        return userService.selectByPrimaryKey(userId);
    }

}
