package com.example.activitymanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.activitymanage.common.CommonResponse;
import com.example.activitymanage.response.PrizeResponse;
import com.example.activitymanage.service.RecordService;

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

    @ApiOperation(value = "getLottery", tags = "抽奖接口")
    @GetMapping("/getLottery")
    public CommonResponse<PrizeResponse> getLottery(
            @ApiParam(name = "weChatId", value = "weChatId") @RequestParam String weChatId,
            @ApiParam(name = "actId", value = "actId") @RequestParam Integer actId
    ) {
        return CommonResponse.success("抽奖接口", recordService.getLottery(weChatId, actId));
    }
}
