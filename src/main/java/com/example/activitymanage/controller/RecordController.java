package com.example.activitymanage.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.activitymanage.common.CommonResponse;
import com.example.activitymanage.model.User;
import com.example.activitymanage.response.PrizeResponse;
import com.example.activitymanage.response.RecordResponse;
import com.example.activitymanage.service.RecordService;
import com.example.activitymanage.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-14
 */

@Api(value = "RecordController", tags = "抽奖接口", description = "测试接口，纯属娱乐，不用管")
@RestController
@RequestMapping(value = "/lottery")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "getLottery", tags = "抽奖接口")
    @GetMapping("/getLottery")
    public CommonResponse<PrizeResponse> getLottery(
            @ApiParam(name = "weChatId", value = "weChatId") @RequestParam String weChatId,
            @ApiParam(name = "actId", value = "actId") @RequestParam Integer actId
    ) throws ParseException {
        return CommonResponse.success("抽奖接口", recordService.getLottery(weChatId, actId));
    }

    @ApiOperation(value = "", tags = "")
    @GetMapping("/getRecord")
    public CommonResponse<List<RecordResponse>> getRecordByActIdOrUserId(
            @ApiParam(name = "actId", value = "活动id") @RequestParam(required = false) Integer actId,
            @ApiParam(name = "weChatId", value = "微信id") @RequestParam(required = false) String weChatId
    ) {
        User user = userService.selectByWeChatId("ofH-b5RjoWtBs5AU_eJLLDJil7VA");
        return user == null ? CommonResponse.success("根据活动id或微信id获取记录", null) :
               CommonResponse.success("根据活动id或微信id获取记录",
                       recordService.getRecordByActIdOrUserId(actId, user.getId()));
    }
}
