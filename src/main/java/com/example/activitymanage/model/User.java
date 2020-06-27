package com.example.activitymanage.model;

import java.util.List;

import com.example.activitymanage.request.UserRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;

    private String weChatId;

    private String weChatName;

    private String name;

    private String phone;

    private String email;

    private String address;

    public static User convertFrom(UserRequest request) {
        if (request == null) {
            return null;
        }
        StringBuilder address = new StringBuilder();
        List<String> area = request.getArea();
        area.forEach(a -> {
            address.append(a).append(" ");
        });
        address.append(request.getDetailAddress());
        return User.builder()
                .weChatId(request.getWeChatId())
                .weChatName(request.getWeChatName())
                .name(request.getName())
                .phone(request.getPhone())
                .address(address.toString())
                .build();
    }
}