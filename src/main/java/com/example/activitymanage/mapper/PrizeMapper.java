package com.example.activitymanage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.activitymanage.model.Prize;

@Repository
@Mapper
public interface PrizeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Prize record);

    Prize selectByPrimaryKey(Integer id);

    List<Prize> selectAll();

    int updateByPrimaryKey(Prize record);

    void insertPrizeList(@Param("prizeList") List<Prize> prizeList);

    List<Prize> selectByActId(@Param("actId") Integer actId);
}