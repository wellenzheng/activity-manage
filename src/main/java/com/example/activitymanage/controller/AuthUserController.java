package com.example.activitymanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.activitymanage.common.CommonIdResponse;
import com.example.activitymanage.common.CommonResponse;
import com.example.activitymanage.model.AuthUser;
import com.example.activitymanage.service.AuthUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-22
 */

@Api(value = "AuthUserController", tags = "AuthUserController")
@RestController
@RequestMapping("/authUser")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;

    private JavaMailSenderImpl mailSender;

    @ApiOperation(value = "edit", tags = "edit")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/edit")
    public CommonResponse<CommonIdResponse> editAuthUser(@ApiParam(name = "authUser", value = "authUser")
    @RequestBody AuthUser authUser) {
        Integer id = authUserService.editAuthUser(authUser);
        return CommonResponse.success("修改管理员信息",
                CommonIdResponse.builder().authUserId(id).build());
    }

    @ApiOperation(value = "sendEmail", tags = "sendEmail")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/sendEmail")
    public void sendEmail(String to, String from, String message) {
        SimpleMailMessage message1;
    }
}
