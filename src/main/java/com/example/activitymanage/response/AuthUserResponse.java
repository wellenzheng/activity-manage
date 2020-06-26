package com.example.activitymanage.response;

import com.example.activitymanage.model.AuthUser;

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
public class AuthUserResponse {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("角色")
    private String role;

    public static AuthUserResponse convert(AuthUser authUser) {
        return AuthUserResponse.builder()
                .id(authUser.getId())
                .username(authUser.getUsername())
                .name(authUser.getName())
                .email(authUser.getEmail())
                .role(authUser.getRole())
                .build();
    }
}
