package com.example.activitymanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.activitymanage.common.CommonIdResponse;
import com.example.activitymanage.common.CommonResponse;
import com.example.activitymanage.model.User;
import com.example.activitymanage.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-14
 */

@Api(value = "UserController", tags = "UserController")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ApiOperation(value = "获取所有用户列表")
    public CommonResponse<List<User>> getAllUsers() {
        return CommonResponse.success("获取所有用户列表", userService.getAllUsers());
    }

    @GetMapping("/addUser")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ApiOperation(value = "添加用户")
    public CommonResponse<CommonIdResponse> addUser(@ApiParam(name = "user", value = "用户") @RequestBody User user) {
        return CommonResponse.success("添加用户", CommonIdResponse.builder().userId(userService.addUser(user)).build());
    }
}
