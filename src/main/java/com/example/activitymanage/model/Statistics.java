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
public class Statistics {
    private Integer id;

    private Integer activityId;

    private String type;

    private Integer number;

    private Date date;
}