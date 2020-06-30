package com.example.activitymanage.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhengweijun
 * Created on 2020-06-23
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AuthUserRequest", description = "管理员信息详情")
public class AuthUserRequest {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("姓名")
    private String name;
}
