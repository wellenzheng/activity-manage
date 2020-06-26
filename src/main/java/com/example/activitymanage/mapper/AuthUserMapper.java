package com.example.activitymanage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.activitymanage.model.AuthUser;

@Repository
@Mapper
public interface AuthUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthUser record);

    AuthUser selectByPrimaryKey(Integer id);

    List<AuthUser> selectAll();

    AuthUser selectByUsername(@Param("username") String username);

    int updateByPrimaryKey(AuthUser record);

    void updateByUsername(@Param("authUser") AuthUser authUser);
}