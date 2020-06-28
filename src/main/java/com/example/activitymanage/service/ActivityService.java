package com.example.activitymanage.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitymanage.enums.StatusEnum;
import com.example.activitymanage.mapper.ActivityMapper;
import com.example.activitymanage.model.Activity;
import com.example.activitymanage.model.Prize;
import com.example.activitymanage.model.Statistics;
import com.example.activitymanage.request.ActivityRequest;
import com.example.activitymanage.request.PrizeRequest;
import com.example.activitymanage.response.ActivityResponse;
import com.example.activitymanage.utils.DateFormatUtils;

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

    public List<ActivityResponse> getAllActivities() {
        activityMapper.updateActivityStatus();
        List<Activity> activityList = activityMapper.selectAll();
        if (activityList == null || activityList.size() == 0) {
            return null;
        }
        List<ActivityResponse> activityResponseList = new ArrayList<>(activityList.size());
        activityList.forEach(activity -> {
            try {
                activityResponseList.add(convert(activity));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return activityResponseList;
    }

    public ActivityResponse getActivityById(Integer id) throws ParseException {
        activityMapper.updateActivityStatus();
        Activity activity = activityMapper.selectByPrimaryKey(id);
        return activity == null ? null : convert(activity);
    }

    public Integer addActivity(ActivityRequest activityRequest) {
        if (activityRequest == null) {
            return null;
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
        return activityMapper.updateById(id, Activity.convertFrom(activityRequest)) > 0 ? id : null;
    }

    private ActivityResponse convert(Activity activity) throws ParseException {
        ActivityResponse activityResponse = Activity.convertTo(activity);
        Map<String, List<Statistics>> statistics =
                recordService.getStatisticsByActId(activity.getId(), activity.getStartTime(), activity.getEndTime());
        activityResponse.setPartNumber(recordService.getCountByActId(activity.getId()));
        activityResponse.setPrizes(prizeService.getPrizeListByActId(activity.getId()));
        activityResponse.setStatistics(statistics);
        return activityResponse;
    }

    public Integer deleteById(Integer id) {
        if (id == null || id <= 0) {
            return null;
        }
        return activityMapper.deleteByPrimaryKey(id);
    }
}
