package com.example.activitymanage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    private Integer id;

    private String type;

    private Integer activityId;

    private Integer prizeId;

    private Integer userId;

    private Integer isLucky;

    private String date;
}