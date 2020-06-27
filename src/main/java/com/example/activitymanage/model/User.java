package com.example.activitymanage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;

    private Integer weChatId;

    private String weChatName;

    private String name;

    private String phone;

    private String email;

    private String address;
}