package com.example.activitymanage.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.activitymanage.mapper.ActivityMapper;
import com.example.activitymanage.model.Activity;
import com.example.activitymanage.model.AuthUser;
import com.example.activitymanage.model.Prize;
import com.example.activitymanage.request.ActivityRequest;
import com.example.activitymanage.request.PrizeRequest;
import com.example.activitymanage.response.ActivityResponse;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-13
 */

@Service
public class ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private PrizeService prizeService;

    @Autowired
    private RecordService recordService;

    public List<Activity> getAllActivities() {
        return activityMapper.selectAll();
    }

    public ActivityResponse getActivityById(Integer id) {
        Activity activity = activityMapper.selectByPrimaryKey(id);
        ActivityResponse activityResponse = Activity.convertTo(activity);
        activityResponse.setPrizes(prizeService.getPrizeListByActId(activity.getId()));
        activityResponse.setStatistics(
                recordService.getStatisticsByActId(activity.getId(), "2020-06-26",
                        "2020-07-01"));
        return activityResponse;
    }

    public Integer addActivity(ActivityRequest activityRequest) {
        if (activityRequest == null) {
            return -1;
        }
        Activity activity = Activity.convertFrom(activityRequest);
        activityMapper.insert(activity);
        List<PrizeRequest> prizeData = activityRequest.getPrizeData();
        List<Prize> prizeList = new ArrayList<>(prizeData.size());
        prizeData.forEach(prizeRequest -> {
            prizeList.add(Prize.convertFrom(activity.getId(), prizeRequest));
        });
        if (prizeList.size() != 0) {
            prizeService.addPrizeList(prizeList);
        }
        return activity.getId();
    }

    public Integer editActivityById(Integer id, ActivityRequest activityRequest) {
        return id;
    }
}
