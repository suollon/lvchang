package com.suollon.lvchang.controller;

import com.suollon.lvchang.domain.entity.User;
import com.suollon.lvchang.domain.model.UserModel;
import com.suollon.lvchang.domain.vo.UserAddVO;
import com.suollon.lvchang.domain.vo.UserUpdateVO;
import com.suollon.lvchang.nonbusiness.util.BeanCopierUtil;
import com.suollon.lvchang.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@RestController
@RequestMapping("/user")
@Api(description = "用户操作类")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ApiOperation("新增用户信息")
    public int add(@RequestBody @Validated UserAddVO userAddVO) {
        User user = User.build()
                .userName(userAddVO.getUserName())
                .address(userAddVO.getAddress())
                .phone(userAddVO.getPhone())
                .createTime(new Date())
                .updateTime(new Date());
        return userService.insertSelective(user);
    }

    @PutMapping
    @ApiOperation("更新用户信息")
    public int update(@RequestBody @Validated UserUpdateVO userUpdateVO) {
        User user = User.build()
                .userId(userUpdateVO.getUserId())
                .userName(userUpdateVO.getUserName())
                .address(userUpdateVO.getAddress())
                .phone(userUpdateVO.getPhone())
                .updateTime(new Date());
        return userService.updateByPrimaryKeySelective(user);
    }

    @GetMapping
    @ApiOperation("按用户ID查询用户信息")
    public UserModel get(@RequestParam("userId") @Validated @NotNull(message = "用户ID不能为空") @ApiParam("用户ID") Long userId) {
        return BeanCopierUtil.copyForClass(userService.selectByPrimaryKey(userId), UserModel.class);
    }
}
