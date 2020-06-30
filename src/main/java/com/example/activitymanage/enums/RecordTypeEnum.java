package com.example.activitymanage.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhengweijun
 * Created on 2020-06-12
 */
public enum RecordTypeEnum {

    LUCKYDIP("抽奖"),
    ENROLL("报名"),
    GROUPSHOP("拼团"),
    CUTTING("砍价");

    private String desc;

    RecordTypeEnum(String desc) {
        this.desc = desc;
    }

    private static final Map<String, RecordTypeEnum> MAP = Arrays.stream(values())
            .collect(Collectors.toMap(RecordTypeEnum::name, Function.identity(), (o, n) -> o));

    public static RecordTypeEnum ofDesc(String desc) {
        return MAP.get(desc);
    }
}
