package com.example.activitymanage.request;

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
public class PrizeRequest {

    @ApiModelProperty("奖品序号")
    private Integer seq;

    @ApiModelProperty("奖品等级")
    private String prizeRank;

    @ApiModelProperty("奖品名称")
    private String prizeName;

    @ApiModelProperty("奖品总数")
    private Integer totalNumber;

    @ApiModelProperty("中奖概率")
    private Double probability;

    @ApiModelProperty("是否中奖")
    private String isLucky;
}
