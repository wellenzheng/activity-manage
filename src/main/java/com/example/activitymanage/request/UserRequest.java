package com.example.activitymanage.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "UserRequest", description = "用户信息")
public class UserRequest {

    @ApiModelProperty("微信id")
    private String weChatId;

    @ApiModelProperty("微信名")
    private String weChatName;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("微信名")
    private String email;

    @ApiModelProperty("所在地区")
    private List<String> area;

    @ApiModelProperty("详细地址")
    private String detailAddress;
}
