package com.example.activitymanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitymanage.mapper.UserMapper;
import com.example.activitymanage.model.User;
import com.example.activitymanage.request.UserRequest;

/**
 * @author zhengweijun
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

    public User selectByWeChatId(String weChaId) {
        return userMapper.selectByWeChatId(weChaId);
    }

    public Integer editUser(UserRequest userRequest) {
        User user = User.convertFrom(userRequest);
        return user == null ? null : userMapper.updateUser(user);
    }

    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }
}
