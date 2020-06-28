package com.example.activitymanage.response;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.activitymanage.model.Statistics;
import com.example.activitymanage.request.PrizeRequest;

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
    private String createTime;

    @ApiModelProperty("创建者")
    private String creator;

    @ApiModelProperty("活动开始时间")
    private String actStartTime;

    @ApiModelProperty("活动开始时间")
    private String actEndTime;

    @ApiModelProperty("奖品信息")
    private List<PrizeResponse> prizes;

    @ApiModelProperty("统计信息")
    private Map<String, List<Statistics>> statistics;

    @ApiModelProperty("用户类型")
    private String userTypeRadio;

    @ApiModelProperty("活动描述")
    private String actDesc;

    @ApiModelProperty("抽奖次数限制类型")
    private String limitTypeRadio;

    @ApiModelProperty("抽奖限制次数")
    private Integer limitTimes;

    @ApiModelProperty("虚拟参与人数")
    private Integer virtualPars;
}
