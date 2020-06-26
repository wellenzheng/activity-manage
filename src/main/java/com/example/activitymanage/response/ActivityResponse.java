package com.example.activitymanage.response;

import java.util.Date;

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
public class ActivityResponse {

    @ApiModelProperty("主键id")
    private Integer actId;

    @ApiModelProperty("主键id")
    private String actName;

    @ApiModelProperty("主键id")
    private String actType;

    @ApiModelProperty("主键id")
    private String actStatus;

    @ApiModelProperty("主键id")
    private Integer partNumber;

    @ApiModelProperty("主键id")
    private Date createTime;

    @ApiModelProperty("主键id")
    private String creator;
}
