package com.example.activitymanage.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-14
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserPrizeResponse", description = "用户在某个活动的获奖信息")
public class UserPrizeResponse {
    @ApiModelProperty("奖品名，包括谢谢参与")
    private String prizeName;

    @ApiModelProperty("日期")
    private Date date;
}
