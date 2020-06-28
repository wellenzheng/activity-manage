package com.example.activitymanage.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-27
 */


public enum UserTypeEnum {

    WECHAT("微信用"),
    SEEWO("希沃用户");

    private String desc;

    UserTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    private static final Map<String, UserTypeEnum> MAP = Arrays.stream(values())
            .collect(Collectors.toMap(UserTypeEnum::name, Function.identity(), (o, n) -> o));

    public static UserTypeEnum ofName(String name) {
        return MAP.get(name);
    }

    public static String getDescByName(String name) {
        return MAP.get(name) == null ? null : MAP.get(name).getDesc();
    }
}
