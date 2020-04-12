package com.suollon.lvchang.controller;

import com.suollon.lvchang.domain.entity.User;
import com.suollon.lvchang.domain.model.UserModel;
import com.suollon.lvchang.domain.vo.UserAddVO;
import com.suollon.lvchang.domain.vo.UserUpdateVO;
import com.suollon.lvchang.nonbusiness.exceptionhandler.ErrorEnum;
import com.suollon.lvchang.nonbusiness.exceptionhandler.RespModel;
import com.suollon.lvchang.nonbusiness.exceptionhandler.StandardException;
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
    public RespModel add(@RequestBody @Validated UserAddVO userAddVO) {
        User user = User.build()
                .userName(userAddVO.getUserName())
                .address(userAddVO.getAddress())
                .phone(userAddVO.getPhone())
                .createTime(new Date())
                .updateTime(new Date());
        userService.insertSelective(user);
        return RespModel.success();
    }

    @PutMapping
    @ApiOperation("更新用户信息")
    public RespModel update(@RequestBody @Validated UserUpdateVO userUpdateVO) {
        if (userUpdateVO.getUserId() == 1) {
            throw new StandardException(ErrorEnum.E4001000, "超级管理员的用户信息不允许修改");
        }

        User user = User.build()
                .userId(userUpdateVO.getUserId())
                .userName(userUpdateVO.getUserName())
                .address(userUpdateVO.getAddress())
                .phone(userUpdateVO.getPhone())
                .updateTime(new Date());
        userService.updateByPrimaryKeySelective(user);
        return RespModel.success();
    }

    @GetMapping
    @ApiOperation("按用户ID查询用户信息")
    public RespModel<UserModel> get(@RequestParam("userId") @Validated @NotNull(message = "用户ID不能为空") @ApiParam("用户ID") Long userId) {
        UserModel userModel = BeanCopierUtil.copyForClass(userService.selectByPrimaryKey(userId), UserModel.class);
        return RespModel.success(userModel);
    }
}
