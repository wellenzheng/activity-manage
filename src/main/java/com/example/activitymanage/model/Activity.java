package com.example.activitymanage.model;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.example.activitymanage.enums.ActivityTypeEnum;
import com.example.activitymanage.enums.LimitTypeEnum;
import com.example.activitymanage.enums.StatusEnum;
import com.example.activitymanage.enums.UserTypeEnum;
import com.example.activitymanage.request.ActivityRequest;
import com.example.activitymanage.response.ActivityResponse;
import com.example.activitymanage.utils.DateFormatUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    private Integer id;

    private String name;

    private String type;

    private String status;

    private String userType;

    private String limitType;

    private Integer limitTimes;

    private String creator;

    private String createTime;

    private String startTime;

    private String endTime;

    private String description;

    private Integer virtualPars;

    public static Activity convertFrom(ActivityRequest request) {
        if (request == null) {
            return null;
        }
        List<Date> actDateTime = request.getActDateTime();
        Date startTime = actDateTime.get(0);
        Date endTime = actDateTime.get(1);
        return Activity.builder()
                .name(request.getActName())
                .type(request.getActTypeRadio())
                .limitType(request.getLimitTypeRadio())
                .limitTimes(request.getLimitTimes())
                .status(request.getActStatus() == null ? StatusEnum.UNPUBLISHED.name() : request.getActStatus())
                .userType(request.getUserTypeRadio())
                .createTime(DateFormatUtils.getClock(new Date()))
                .startTime(DateFormatUtils.get0Clock(startTime))
                .endTime(DateFormatUtils.get24Clock(endTime))
                .creator(request.getCreator())
                .description(request.getActDesc())
                .virtualPars(request.getVirtualPars())
                .build();
    }

    public static ActivityResponse convertTo(Activity activity) throws ParseException {
        return activity == null ? null : ActivityResponse.builder()
                .actId(activity.getId())
                .actName(activity.getName())
                .actType(ActivityTypeEnum.getDescByName(activity.getType()))
                .actStatus(StatusEnum.getDescByName(activity.getStatus()))
                .userTypeRadio(UserTypeEnum.getDescByName(activity.getUserType()))
                .createTime(activity.getCreateTime())
                .creator(activity.getCreator())
                .actStartTime(activity.getStartTime())
                .actEndTime(activity.getEndTime())
                .actDesc(activity.getDescription())
                .limitTypeRadio(LimitTypeEnum.getDescByName(activity.getLimitType()))
                .limitTimes(activity.getLimitTimes())
                .virtualPars(activity.getVirtualPars())
                .build();
    }
}