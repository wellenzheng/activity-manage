package com.example.activitymanage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.activitymanage.model.Activity;

@Repository
@Mapper
public interface ActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    Activity selectByPrimaryKey(Integer id);

    List<Activity> selectAll();

    int updateById(@Param("id") Integer id, @Param("act") Activity act);

    void updateActivityStatus();
}