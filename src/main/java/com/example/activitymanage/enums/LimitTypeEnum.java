package com.example.activitymanage.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-12
 */
public enum LimitTypeEnum {

    EVERYDAY("每日限定"),
    ALLALONG("全程限定");

    private String desc;

    LimitTypeEnum(String desc) {
        this.desc = desc;
    }

    private static final Map<String, LimitTypeEnum> MAP = Arrays.stream(values())
            .collect(Collectors.toMap(LimitTypeEnum::name, Function.identity(), (o, n) -> o));

    public static LimitTypeEnum ofDesc(String desc) {
        return MAP.get(desc);
    }
}
