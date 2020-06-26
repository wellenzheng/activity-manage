package com.example.activitymanage.request;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-15
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ActivityRequest", description = "活动请求详情")
public class ActivityRequest {

    @ApiModelProperty("活动名称")
    private String actName;

    @ApiModelProperty("活动类型")
    private String actTypeRadio;

    @ApiModelProperty("活动时间")
    private List<Date> actDateTime;

    @ApiModelProperty("用户类型")
    private String userTypeRadio;

    @ApiModelProperty("活动状态")
    private String actStatus;

    @ApiModelProperty("创建者名字")
    private String creator;

    @ApiModelProperty("奖品列表")
    private List<PrizeRequest> prizeData;

    @ApiModelProperty("活动描述")
    private String actDesc;

    @ApiModelProperty("抽奖次数限制类型")
    private String limitTypeRadio;

    @ApiModelProperty("抽奖限制次数")
    private Integer limitTimes;

    @ApiModelProperty("虚拟参与人数")
    private Integer virtualPars;
}
