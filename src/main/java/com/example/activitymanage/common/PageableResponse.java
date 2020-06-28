package com.example.activitymanage.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-28
 */

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@JsonInclude(Include.NON_NULL)
public class PageableResponse<R> {

    private Integer pageNum;

    private Integer pageSize;

    private Long total;

    private List<R> list;

    public static <R> PageableResponse<R> pageableResponse(Integer pageNum, Integer pageSize,
            Long total, List<R> list) {
        return PageableResponse.of(pageNum, pageSize, total, list);
    }
}
