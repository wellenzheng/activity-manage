package com.example.activitymanage.response;

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
@AllArgsConstructor
@ApiModel(value = "AuthUserResponse", description = "管理员信息详情")
public class UserResponse {
    @ApiModelProperty("主键id")
    private Integer id;
}
