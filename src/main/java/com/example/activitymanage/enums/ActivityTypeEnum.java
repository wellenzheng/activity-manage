package com.example.activitymanage.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-12
 */
public enum ActivityTypeEnum {

    INDEPENDENT("独立活动"),
    SYSTEM("系统活动");

    private String desc;

    ActivityTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    private static final Map<String, ActivityTypeEnum> MAP = Arrays.stream(values())
            .collect(Collectors.toMap(ActivityTypeEnum::name, Function.identity(), (o, n) -> o));

    public static ActivityTypeEnum ofName(String name) {
        return MAP.get(name);
    }

    public static String getDescByName(String name) {
        return MAP.get(name) == null ? null : MAP.get(name).getDesc();
    }
}
