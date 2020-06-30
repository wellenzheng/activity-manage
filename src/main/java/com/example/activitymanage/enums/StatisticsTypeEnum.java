package com.example.activitymanage.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhengweijun
 * Created on 2020-06-12
 */
public enum StatisticsTypeEnum {

    VIEW("浏览"),
    PARTICIPATE("参与"),
    AWARD("获奖"),
    SHARE("分享");

    private String desc;

    StatisticsTypeEnum(String desc) {
        this.desc = desc;
    }

    private static final Map<String, StatisticsTypeEnum> MAP = Arrays.stream(values())
            .collect(Collectors.toMap(StatisticsTypeEnum::name, Function.identity(), (o, n) -> o));

    public static StatisticsTypeEnum ofDesc(String desc) {
        return MAP.get(desc);
    }
}
