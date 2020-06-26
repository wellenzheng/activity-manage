package com.example.activitymanage.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-12
 */

public enum StatusEnum {

    UNPUBLISHED("未发布"),
    PUBLISHED("已发布"),
    FINISHED("已结束");

    private String desc;

    StatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    private static final Map<String, StatusEnum> MAP = Arrays.stream(values())
            .collect(Collectors.toMap(StatusEnum::name, Function.identity(), (o, n) -> o));

    public static StatusEnum ofName(String name) {
        return MAP.get(name);
    }

    public static String getDescByName(String name) {
        return MAP.get(name) == null ? null : MAP.get(name).getDesc();
    }
}
