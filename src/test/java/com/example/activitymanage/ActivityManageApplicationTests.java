package com.example.activitymanage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.activitymanage.mapper.ActivityMapper;
import com.example.activitymanage.mapper.PrizeMapper;
import com.example.activitymanage.mapper.RecordMapper;
import com.example.activitymanage.mapper.StatisticsMapper;
import com.example.activitymanage.mapper.UserMapper;
import com.example.activitymanage.model.Prize;
import com.example.activitymanage.model.Record;
import com.example.activitymanage.service.ActivityService;
import com.example.activitymanage.service.RecordService;
import com.example.activitymanage.utils.DateFormatUtils;

@SpringBootTest
class ActivityManageApplicationTests {

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ActivityService activityService;

    @Autowired
    PrizeMapper prizeMapper;

    @Autowired
    RecordMapper recordMapper;

    @Autowired
    RecordService recordService;

    @Autowired
    StatisticsMapper statisticsMapper;

    @Test
    void contextLoads() {
        List<Prize> prizeList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            prizeList.add(Prize.builder()
                    .activityId(i)
                    .name(String.valueOf(i))
                    .ranking(String.valueOf(i))
                    .totalNumber(i)
                    .probability(0.1)
                    .collectedNumber(0)
                    .isLucky(0)
                    .build());
        }
        prizeMapper.insertPrizeList(prizeList);
    }


    @Test
    void text1() {
        recordMapper.insert(Record.builder()
                .userId(1)
                .activityId(1)
                .type("1")
                .prizeId(1)
                .date(DateFormatUtils.get12Clock(new Date()))
                .build());
    }

    @Test
    void test2() throws ParseException {
        activityMapper.updateActivityStatus();
    }

    @Test
    void test3() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.SEPTEMBER, 10);
        System.out.println(DateFormatUtils.get0Clock(calendar.getTime()));
    }
}
