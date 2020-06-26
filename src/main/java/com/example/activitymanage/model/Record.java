package com.example.activitymanage.model;

import java.util.Date;

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

    private Date date;
}