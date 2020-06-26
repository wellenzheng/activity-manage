package com.example.activitymanage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.activitymanage.mapper.AuthUserMapper;
import com.example.activitymanage.model.AuthUser;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-12
 */

@Service
public class AuthUserService implements UserDetailsService {

    @Autowired
    private AuthUserMapper authUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authUserMapper.selectByUsername(username);
        if (authUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return authUser;
    }

    public Integer editAuthUser(AuthUser authUser) {
        System.out.println(authUser);
        AuthUser au = authUserMapper.selectByUsername(authUser.getUsername());
        if (au == null) {
            return -1;
        }
        authUserMapper.updateByUsername(authUser);
        return au.getId();
    }
}
