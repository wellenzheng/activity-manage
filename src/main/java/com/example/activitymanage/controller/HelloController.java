package com.example.activitymanage.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.activitymanage.common.CommonResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author zhengweijun
 * Created on 2020-06-13
 */

@Api(value = "HelloController", tags = "HelloController", description = "测试接口，纯属娱乐，不用管")
@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @GetMapping("/hello")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ApiOperation(value = "测试hello")
    public CommonResponse<String> hello() {
        return CommonResponse.success("成功", "hello");
    }

    @GetMapping("/hello/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ApiOperation(value = "测试hello/{id}")
    public CommonResponse<Integer> helloId(@ApiParam(name = "id", value = "id", required = true)
    @PathVariable Integer id, Authentication authentication) {
        authentication.getPrincipal();
        return CommonResponse.success("成功", id);
    }

    @GetMapping("/addHello")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ApiOperation(value = "测试addHello")
    public CommonResponse<String> addHello(@ApiParam(name = "id", value = "id", required = true)
    @RequestParam String hello) {
        return CommonResponse.success("成功", hello);
    }

    @GetMapping("/editHello")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ApiOperation(value = "测试editHello")
    public CommonResponse<String> editHello(@ApiParam(name = "id", value = "id", required = true)
    @RequestParam String hello) {
        return CommonResponse.success("成功", hello);
    }
}
