package com.example.activitymanage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.activitymanage.common.CommonIdResponse;
import com.example.activitymanage.common.CommonResponse;
import com.example.activitymanage.mapper.ActivityMapper;
import com.example.activitymanage.model.Activity;
import com.example.activitymanage.model.Prize;
import com.example.activitymanage.model.User;
import com.example.activitymanage.response.ActivityResponse;
import com.example.activitymanage.response.UserPrizeResponse;
import com.example.activitymanage.request.UserRequest;
import com.example.activitymanage.response.WXLoginResponse;
import com.example.activitymanage.service.ActivityService;
import com.example.activitymanage.service.PrizeService;
import com.example.activitymanage.service.RecordService;
import com.example.activitymanage.service.UserService;
import com.example.activitymanage.service.WXLoginService;
import com.example.activitymanage.utils.HttpUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-14
 */

@Api(value = "UserController", tags = "UserController")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WXLoginService wxLoginService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private PrizeService prizeService;

    @Autowired
    private ActivityMapper activityMapper;

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ApiOperation(value = "获取所有用户列表")
    public CommonResponse<List<User>> getAllUsers() {
        return CommonResponse.success("获取所有用户列表", userService.getAllUsers());
    }

    @GetMapping("/addUser")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ApiOperation(value = "添加用户")
    public CommonResponse<CommonIdResponse> addUser(@ApiParam(name = "user", value = "用户") @RequestBody User user) {
        return CommonResponse.success("添加用户", CommonIdResponse.builder().userId(userService.addUser(user)).build());
    }

    @PostMapping("/editUser")
    @ApiOperation(value = "编辑用户信息")
    public CommonResponse<CommonIdResponse> editUser(
            @ApiParam(name = "userRequest", value = "用户信息") @RequestBody UserRequest userRequest
    ) {
        return CommonResponse.success("编辑用户信息", CommonIdResponse.builder()
                .userId(userService.editUser(userRequest)).build());
    }

    @GetMapping("/wxlogin")
    @ApiOperation(value = "小程序登录")
    public CommonResponse<WXLoginResponse> wxLogin(@RequestParam("code") String code) {

        Map<String, String> param = new HashMap<>();
        param.put("js_code", code);
        param.put("appid", wxLoginService.LOGIN_APP_ID);
        param.put("secret", wxLoginService.LOGIN_APP_SECRET);
        param.put("grant_type", wxLoginService.LOGIN_GRANT_TYPE);

        String result = HttpUtils.doGet(wxLoginService.WX_LOGIN_URL, param);
        JSONObject jsonRes = new JSONObject(result);
        result = jsonRes.get("openid").toString();

        //        return CommonResponse.success("成功",result);
        List<Activity> allAct = activityMapper.selectAll();
        Activity choosen = null;
        for (Activity act : allAct) {
            if (act.getStatus().equals("PUBLISHED")) {
                choosen = act;
                break;
            }
        }
        //
        //if无合适act
        //else
        User user = userService.selectByWeChatId(result);
        Integer leftChance = null;
        if (user == null) {
            userService.addUser(User.builder().weChatId(result).build());
            leftChance = choosen.getLimitTimes();
        } else {
            leftChance = choosen.getLimitTimes() - recordService.countByUserAndAct(user.getId(), choosen.getId());
        }


        //        UserAct userAct=userActService.selectByUserIdAndActId(result,choosen.getId());
        //
        //        if(userAct==null){
        //            userAct=new UserAct();
        //            userAct.setAct_id(choosen.getId());
        //            userAct.setUser_id(result);
        //            userAct.setLeft_chance(choosen.getLimitTimes());
        //            userAct.setModify_date(new Date());
        //
        //            userActService.insert(userAct);
        //        }
        //
        List<Prize> allPrize = prizeService.selectByActId(choosen.getId());
        List<String> allPrizeName = new ArrayList<>();
        for (Prize prize : allPrize) {
            allPrizeName.add(prize.getName());
        }
        //
        WXLoginResponse wxLoginResponse = new WXLoginResponse();
        wxLoginResponse.setLeft_chance(leftChance);
        wxLoginResponse.setPrizeName(allPrizeName);
        wxLoginResponse.setMyId(result);
        wxLoginResponse.setActId(choosen.getId());

        return CommonResponse.success("小程序登录", wxLoginResponse);
    }

    @GetMapping("/prizeRecord")
    @ApiOperation(value = "小程序用户中奖记录")
    public CommonResponse<List<UserPrizeResponse>> personPrizeRecordInOneAct(@RequestParam("weChatId") String weChatId,
            @RequestParam("actId") Integer actId) {
        User user = userService.selectByWeChatId(weChatId);
        return CommonResponse.success("用户中奖记录", recordService.getPersonPrizeOneAct(user.getId(), actId));
    }
}
