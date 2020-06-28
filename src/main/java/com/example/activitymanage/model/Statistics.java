package com.example.activitymanage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {
    private Integer activityId;

    private Integer isLucky;

    private String date;

    private Integer count;
}