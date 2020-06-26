package com.example.activitymanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitymanage.mapper.PrizeMapper;
import com.example.activitymanage.model.Prize;

import io.swagger.models.auth.In;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-14
 */

@Service
public class PrizeService {

    @Autowired
    private PrizeMapper prizeMapper;

    public void addPrizeList(List<Prize> prizeList){
        prizeMapper.insertPrizeList(prizeList);
    }
}
