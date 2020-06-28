package com.example.activitymanage.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-13
 */

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@JsonInclude(Include.ALWAYS)
public class CommonResponse<R> {
    private Integer code;
    private String message;
    private R response;

    public static <R> CommonResponse<R> success(String message, R response) {
        return CommonResponse.of(200, message, response);
    }

    public static <R> CommonResponse<R> error(String message, R response) {
        return CommonResponse.of(500, message, response);
    }
}
