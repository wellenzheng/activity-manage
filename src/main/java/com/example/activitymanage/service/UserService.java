package com.example.activitymanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitymanage.mapper.UserMapper;
import com.example.activitymanage.model.User;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-14
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    public Integer addUser(User user) {
        return userMapper.insert(user);
    }

    public User selectByWechatId(String wechat_id){return userMapper.selectByWechatId(wechat_id);}
}
