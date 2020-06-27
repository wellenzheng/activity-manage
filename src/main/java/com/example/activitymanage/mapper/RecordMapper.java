package com.example.activitymanage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.activitymanage.model.Record;
import com.example.activitymanage.response.StatisticsResponse;

@Repository
@Mapper
public interface RecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Record record);

    Record selectByPrimaryKey(Integer id);

    List<Record> selectAll();

    int updateByPrimaryKey(Record record);

    List<StatisticsResponse> getStatisticsByActId(@Param("actId") Integer actId, @Param("startTime") String startTime,
            @Param("endTime") String endTime);

    Integer countByUserAndAct(@Param("userId") Integer userId, @Param("actId") Integer actId,
            @Param("startTime") String startTime, @Param("endTime") String endTime);
}