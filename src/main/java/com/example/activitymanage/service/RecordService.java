package com.example.activitymanage.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitymanage.enums.LimitTypeEnum;
import com.example.activitymanage.enums.RecordTypeEnum;
import com.example.activitymanage.enums.StatisticsTypeEnum;
import com.example.activitymanage.enums.StatusEnum;
import com.example.activitymanage.mapper.ActivityMapper;
import com.example.activitymanage.mapper.RecordMapper;
import com.example.activitymanage.model.Activity;
import com.example.activitymanage.model.Prize;
import com.example.activitymanage.model.Record;
import com.example.activitymanage.model.Statistics;
import com.example.activitymanage.model.User;
import com.example.activitymanage.response.PrizeResponse;
import com.example.activitymanage.response.RecordResponse;
import com.example.activitymanage.response.UserPrizeResponse;
import com.example.activitymanage.utils.DateFormatUtils;

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

    @Autowired
    private UserService userService;

    @Autowired
    private PrizeService prizeService;

    @Autowired
    private ActivityMapper activityMapper;

    public List<RecordResponse> getRecordByActIdOrUserId(Integer actId, Integer userId) {
        List<Record> recordList = recordMapper.getRecordByActIdOrUserId(actId, userId);
        List<RecordResponse> responseList = new ArrayList<>(recordList.size());
        recordList.forEach(record -> {
            RecordResponse convert = convert(record);
            if (convert != null) {
                responseList.add(convert);
            }
        });
        return responseList;
    }

    public Map<String, List<Statistics>> getStatisticsByActId(Integer actId, String startTime, String endTime) {
        if (actId == null || actId <= 0) {
            return null;
        }
        List<Statistics> statisticsList = recordMapper.getStatisticsByActId(actId, startTime, endTime);
        List<Statistics> luckyList = new ArrayList<>();
        List<Statistics> unluckyList = new ArrayList<>();
        List<Statistics> allList = new ArrayList<>();
        statisticsList.forEach(statistics -> {
            if (statistics != null) {
                if (statistics.getIsLucky() == 1) {
                    luckyList.add(statistics);
                } else {
                    unluckyList.add(statistics);
                }
            }
        });
        int i = 0, j = 0;
        while (i < luckyList.size() && j < unluckyList.size()) {
            Statistics lucky = luckyList.get(i);
            Statistics unlucky = unluckyList.get(j);
            if (lucky.getDate().compareTo(unlucky.getDate()) > 0) {
                allList.add(unlucky);
                j++;
            } else if (lucky.getDate().compareTo(unlucky.getDate()) < 0) {
                allList.add(lucky);
                i++;
            } else {
                lucky.setCount(lucky.getCount() + unlucky.getCount());
                allList.add(lucky);
                i++;
                j++;
            }
        }
        while (i < luckyList.size()) {
            allList.add(luckyList.get(i++));
        }
        while (j < unluckyList.size()) {
            allList.add(unluckyList.get(j++));
        }
        Map<String, List<Statistics>> map = new HashMap<>();
        map.put(StatisticsTypeEnum.AWARD.name(), luckyList);
        map.put(StatisticsTypeEnum.PARTICIPATE.name(), allList);
        return map;
    }

    public Integer countByUserAndAct(Integer userId, Integer actId) {
        if (userId == null || userId <= 0 || actId == null || actId <= 0) {
            return null;
        }
        Activity activity = activityMapper.selectByPrimaryKey(actId);
        if (activity == null || !activity.getStatus().equals(StatusEnum.PUBLISHED.name())) {
            return null;
        }
        if (activity.getLimitType().equals(LimitTypeEnum.EVERYDAY.name())) {
            return recordMapper.countByUserAndAct(userId, actId, DateFormatUtils.get0Clock(new Date()),
                    DateFormatUtils.get24Clock(new Date()));
        } else {
            return recordMapper.countByUserAndAct(userId, actId, activity.getStartTime(), activity.getEndTime());
        }
    }

    public PrizeResponse getLottery(String weChatId, Integer actId) throws ParseException {
        activityMapper.updateActivityStatus();
        Activity activity = activityMapper.selectByPrimaryKey(actId);
        if (activity == null || activity.getStatus().equals(StatusEnum.UNPUBLISHED.name()) ||
                activity.getStatus().equals(StatusEnum.FINISHED.name())) {
            return null;
        }
        User user = userService.selectByWeChatId(weChatId);
        Integer userId;
        if (user == null) {
            userId = userService.addUser(User.builder().weChatId(weChatId).build());
        } else {
            userId = user.getId();
        }
        Integer actCount = countByUserAndAct(userId, actId);
        if (actCount == null || actCount >= activity.getLimitTimes()) {
            return null;
        }
        Prize prize = generatePrize(actId);
        if (prize == null) {
            return null;
        }
        if (prize.getIsLucky() == 1) {
            prizeService.incPrizeColNum(prize.getId());
        }
        recordMapper.insert(Record.builder()
                .type(RecordTypeEnum.LUCKYDIP.name())
                .activityId(actId)
                .prizeId(prize.getId())
                .userId(userId)
                .date(DateFormatUtils.get12Clock(new Date()))
                .isLucky(prize.getIsLucky())
                .build());
        return Prize.convertTo(prize);
    }

    private Prize generatePrize(Integer actId) {
        List<Prize> prizeList = prizeService.selectByActId(actId);
        if (prizeList == null || prizeList.size() == 0) {
            return null;
        }
        List<Double> proList = new ArrayList<>();
        AtomicReference<Double> sum = new AtomicReference<>((double) 0);
        AtomicReference<Prize> nonPrize = new AtomicReference<>();
        prizeList.forEach(prize -> {
            if (prize.getIsLucky() == 0) {
                nonPrize.set(prize);
            }
            proList.add(sum.get());
            sum.updateAndGet(v -> v + prize.getProbability());
        });
        proList.add(sum.get());
        double random = Math.random();
        //        System.out.println(prizeList);
        //        System.out.println(proList);
        //        System.out.println(random);
        int prizeIndex = 0;
        for (int i = 0; i < proList.size() - 1; i++) {
            if (random >= proList.get(i) && random <= proList.get(i + 1)) {
                prizeIndex = i;
                break;
            }
        }
        Prize prize = prizeList.get(prizeIndex);
        if (prize.getCollectedNumber() >= prize.getTotalNumber()) {
            return nonPrize.get();
        } else {
            return prize;
        }
    }

    public List<UserPrizeResponse> getPersonPrizeOneAct(Integer userId, Integer actId) {
        return recordMapper.getPersonPrizeOneAct(userId, actId);
    }

    public Integer getCountByActId(Integer id) {
        return recordMapper.getCountByActId(id);
    }

    private RecordResponse convert(Record record) {
        if (record == null) {
            return null;
        }
        Activity activity = activityMapper.selectByPrimaryKey(record.getActivityId());
        return RecordResponse.builder()
                .id(record.getId())
                .type(record.getType())
                .activityName(activity != null ? activity.getName() : "不知道")
                .prize(prizeService.getPrizeById(record.getPrizeId()))
                .user(userService.getUserById(record.getUserId()))
                .date(record.getDate())
                .build();
    }
}
