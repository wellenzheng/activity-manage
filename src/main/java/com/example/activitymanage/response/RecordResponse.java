package com.example.activitymanage.response;

import com.example.activitymanage.model.Prize;
import com.example.activitymanage.model.User;

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
public class RecordResponse {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("记录类型")
    private String type;

    @ApiModelProperty("活动名称")
    private String activityName;

    @ApiModelProperty("奖品")
    private Prize prize;

    @ApiModelProperty("用户")
    private User user;

    @ApiModelProperty("记录时间")
    private String date;
}
