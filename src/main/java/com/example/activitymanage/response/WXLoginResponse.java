package com.example.activitymanage.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-14
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "WXLoginResponse", description = "小程序登录返回结果")
public class WXLoginResponse {

    @ApiModelProperty("我的id")
    private String myId;

    @ApiModelProperty("活动id")
    private Integer actId;

    @ApiModelProperty("剩余次数")
    private Integer left_chance;

    @ApiModelProperty("奖项")
    private List<String> prizeName;

}
