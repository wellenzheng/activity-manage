package com.example.activitymanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.activitymanage.common.CommonIdResponse;
import com.example.activitymanage.model.Activity;
import com.example.activitymanage.request.ActivityRequest;
import com.example.activitymanage.response.ActivityResponse;
import com.example.activitymanage.service.ActivityService;
import com.example.activitymanage.common.CommonResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-13
 */

@Api(value = "ActivityController", tags = "ActivityController")
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/getAllActivities")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ApiOperation(value = "获取所有活动列表")
    public CommonResponse<List<Activity>> getAllActivities() {
        return CommonResponse.success("获取所有活动列表", activityService.getAllActivities());
    }

    @GetMapping("/getActivityById")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ApiOperation(value = "根据id获取活动")
    public CommonResponse<ActivityResponse> getActivityById(
            @ApiParam(name = "id", value = "活动id") @RequestParam Integer id
    ) {
        return CommonResponse.success("根据id获取活动", activityService.getActivityById(id));
    }

    @PostMapping("/addActivity")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ApiOperation(value = "添加活动")
    public CommonResponse<CommonIdResponse> addActivity(
            @ApiParam(name = "activityRequest", value = "activityRequest") @RequestBody ActivityRequest activityRequest
    ) {
        System.out.println(activityRequest);
        return CommonResponse.success("添加活动",
                CommonIdResponse.builder().activityId(activityService.addActivity(activityRequest)).build());
    }

    @PostMapping("/editActivityById")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ApiOperation(value = "根据id编辑活动")
    public CommonResponse<CommonIdResponse> editActivityById(
            @ApiParam(name = "id", value = "活动id") @RequestParam Integer id,
            @ApiParam(name = "activityRequest", value = "activityRequest") @RequestBody ActivityRequest activityRequest
    ) {
        return CommonResponse.success("根据id编辑活动",
                CommonIdResponse.builder().activityId(activityService.editActivityById(id, activityRequest)).build());
    }
}
