package com.example.activitymanage.model;

import org.springframework.data.annotation.Id;

import com.example.activitymanage.request.PrizeRequest;
import com.example.activitymanage.response.PrizeResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prize {

    @Id
    private Integer id;

    private Integer activityId;

    private String ranking;

    private String name;

    private Integer totalNumber;

    private Integer collectedNumber;

    private Double probability;

    private Integer isLucky;

    public static Prize convertFrom(Integer actId, PrizeRequest request) {
        return request == null || actId == null || actId <= 0 ? null : Prize.builder()
                .activityId(actId)
                .ranking(request.getPrizeRank())
                .name(request.getPrizeName())
                .totalNumber(request.getTotalNumber())
                .collectedNumber(0)
                .probability(request.getProbability())
                .isLucky(request.getIsLucky().equals("是") ? 1 : 0)
                .build();
    }

    public static PrizeResponse convertTo(Prize prize) {
        return prize == null ? null : PrizeResponse.builder()
                .id(prize.getId())
                .prizeName(prize.getName())
                .prizeRank(prize.getRanking())
                .totalNumber(prize.getTotalNumber())
                .collectedNumber(prize.getCollectedNumber())
                .build();
    }
}