package com.example.activitymanage.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-14
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AuthUserResponse", description = "管理员信息详情")
public class PrizeResponse {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("奖品名称")
    private String prizeName;

    @ApiModelProperty("奖品等级")
    private String prizeRank;

    @ApiModelProperty("奖品总数")
    private Integer totalNumber;

    @ApiModelProperty("已领取数量")
    private Integer collectedNumber;
}
