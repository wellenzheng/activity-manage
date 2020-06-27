package com.example.activitymanage.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitymanage.mapper.RecordMapper;
import com.example.activitymanage.response.StatisticsResponse;

import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-14
 */

@Slf4j
@Service
public class RecordService {

    @Autowired
    private RecordMapper recordMapper;

    public List<StatisticsResponse> getStatisticsByActId(Integer actId, String startTime, String endTime) {
        return actId == null || actId <= 0 ? null : recordMapper.getStatisticsByActId(actId, startTime, endTime);
    }

    public Integer countByUserAndAct(@Param("user_id")Integer user_id, @Param("act_id")Integer act_id){
        return recordMapper.countByUserAndAct(user_id,act_id);
    }
}
