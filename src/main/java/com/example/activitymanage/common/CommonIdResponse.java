package com.example.activitymanage.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhengweijun
 * Created on 2020-06-14
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@ApiModel(value = "CommonIdResponse", description = "通用id返回对象")
public class CommonIdResponse {

    @ApiModelProperty("管理员id")
    private Integer authUserId;

    @ApiModelProperty("活动id")
    private Integer activityId;

    @ApiModelProperty("奖品id")
    private Integer prizeId;

    @ApiModelProperty("记录id")
    private Integer recordId;

    @ApiModelProperty("人数统计id")
    private Integer statisticsId;

    @ApiModelProperty("活动参与者/用户id")
    private Integer userId;
}
