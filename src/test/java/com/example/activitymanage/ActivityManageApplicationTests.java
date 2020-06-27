package com.example.activitymanage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.activitymanage.enums.RecordTypeEnum;
import com.example.activitymanage.mapper.ActivityMapper;
import com.example.activitymanage.mapper.PrizeMapper;
import com.example.activitymanage.mapper.RecordMapper;
import com.example.activitymanage.mapper.UserMapper;
import com.example.activitymanage.model.Prize;
import com.example.activitymanage.model.Record;

@SpringBootTest
class ActivityManageApplicationTests {

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PrizeMapper prizeMapper;

    @Autowired
    RecordMapper recordMapper;

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
        for (int i = 0; i < 10; i++) {
            recordMapper.insert(Record.builder()
                    .activityId(i)
                    .type(RecordTypeEnum.LUCKYDIP.name())
                    .prizeId(i)
                    .userId(i)
                    .date(new Date())
                    .build());
        }
    }

    @Test
    void test2() {
        System.out.println(recordMapper.getStatisticsByActId(8, "2020-06-26", "2020-06-27"));
    }

    @Test
    void test3(){
        System.out.println(new Date().toString());
//        System.out.println(activityMapper.selectByPrimaryKey(8));
    }
}
