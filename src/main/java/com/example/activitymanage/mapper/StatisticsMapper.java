package com.example.activitymanage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.activitymanage.model.Statistics;

@Repository
@Mapper
public interface StatisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Statistics record);

    Statistics selectByPrimaryKey(Integer id);

    List<Statistics> selectAll();

    int updateByPrimaryKey(Statistics record);
}