package com.example.activitymanage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import com.example.activitymanage.model.User;


@Repository
@Mapper
public interface UserMapper {
    int insert(User user);

    List<User> selectAll();

    User selectByWeChatId(@Param("weChatId") String weChatId);

    int updateUser(@Param("user") User user);

    User selectById(@Param("id") Integer id);
}