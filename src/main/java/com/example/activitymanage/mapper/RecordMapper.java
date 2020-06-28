package com.example.activitymanage.mapper;

import java.util.List;

import com.example.activitymanage.model.Statistics;
import com.example.activitymanage.response.UserPrizeResponse;

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

    int getCountByActId(@Param("actId") Integer actId);

    List<Statistics> getStatisticsByActId(@Param("actId") Integer actId,
            @Param("startTime") String startTime, @Param("endTime") String endTime);

    Integer countByUserAndAct(@Param("user_id") Integer user_id, @Param("act_id") Integer act_id);

    List<UserPrizeResponse> getPersonPrizeOneAct(@Param("user_id") Integer userId, @Param("act_id") Integer actId);

    Integer countByUserAndAct(@Param("userId") Integer userId, @Param("actId") Integer actId,
            @Param("startTime") String startTime, @Param("endTime") String endTime);

    List<Record> getRecordByActIdOrUserId(Integer actId, Integer userId);
}