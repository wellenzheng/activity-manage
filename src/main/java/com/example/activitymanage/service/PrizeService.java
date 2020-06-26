package com.example.activitymanage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activitymanage.mapper.PrizeMapper;
import com.example.activitymanage.model.Prize;
import com.example.activitymanage.response.PrizeResponse;

import io.swagger.models.auth.In;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-14
 */

@Service
public class PrizeService {

    @Autowired
    private PrizeMapper prizeMapper;

    public void addPrizeList(List<Prize> prizeList) {
        prizeMapper.insertPrizeList(prizeList);
    }

    public List<PrizeResponse> getPrizeListByActId(Integer actId) {
        if (actId == null || actId <= 0) {
            return null;
        }
        List<Prize> prizeList = prizeMapper.selectByActId(actId);
        List<PrizeResponse> prizeResponseList = new ArrayList<>(prizeList.size());
        prizeList.forEach(prize -> {
            prizeResponseList.add(Prize.convertTo(prize));
        });
        return prizeResponseList;
    }
}
