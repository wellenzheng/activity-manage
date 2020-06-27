package com.example.activitymanage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.activitymanage.model.User;


@Repository
@Mapper
public interface UserMapper {
    int insert(User user);

    List<User> selectAll();

    User selectByWechatId(@Param("wechat_id")String wechat_id);
}