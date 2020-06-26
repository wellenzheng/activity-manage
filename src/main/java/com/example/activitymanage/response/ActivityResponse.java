package com.example.activitymanage.response;

import java.util.Date;
import java.util.List;

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
@ApiModel(value = "ActivityResponse", description = "管理员信息详情")
public class ActivityResponse {

    @ApiModelProperty("主键id")
    private Integer actId;

    @ApiModelProperty("活动名称")
    private String actName;

    @ApiModelProperty("活动类型")
    private String actType;

    @ApiModelProperty("活动状态")
    private String actStatus;

    @ApiModelProperty("参与人数")
    private Integer partNumber;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("创建者")
    private String creator;

    @ApiModelProperty("奖品信息")
    private List<PrizeResponse> prizes;

    @ApiModelProperty("统计信息")
    private List<StatisticsResponse> statistics;
}
