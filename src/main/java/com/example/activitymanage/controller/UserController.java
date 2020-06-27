package com.example.activitymanage.controller;

import com.example.activitymanage.common.CommonIdResponse;
import com.example.activitymanage.common.CommonResponse;
import com.example.activitymanage.model.User;
import com.example.activitymanage.service.ActivityService;
import com.example.activitymanage.service.PrizeService;
import com.example.activitymanage.service.UserService;
import com.example.activitymanage.service.WXLoginService;
import com.example.activitymanage.utils.HttpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private PrizeService prizeService;

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

    @GetMapping("/wxlogin")
    @ApiOperation(value = "小程序登录")
    public CommonResponse<String> wxLogin(@RequestParam("code") String code){

        Map<String, String> param=new HashMap<>();
        param.put("js_code", code);
        param.put("appid", wxLoginService.LOGIN_APP_ID);
        param.put("secret", wxLoginService.LOGIN_APP_SECRET);
        param.put("grant_type", wxLoginService.LOGIN_GRANT_TYPE);

        String result=HttpUtils.doGet(wxLoginService.WX_LOGIN_URL, param);
        JSONObject jsonRes=new JSONObject(result);
        result=jsonRes.get("openid").toString();

        return CommonResponse.success("成功",result);
//        List<Activity> allAct=activityService.getAllActivities();
//        Activity choosen=null;
//        for(Activity act:allAct){
//            if(act.getStatus().equals("PUBLISHED")){
//                choosen=act;
//                break;
//            }
//        }
//
//        //if无合适act
//        //else
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
//        List<Prize> allPrize=prizeService.selectByActId(choosen.getId());
//        List<String> allPrizeName=new ArrayList<>();
//        for(Prize prize:allPrize){
//            allPrizeName.add(prize.getName());
//        }
//
//        WXLoginResponse wxLoginResponse=new WXLoginResponse();
//        wxLoginResponse.setLeft_chance(userAct.getLeft_chance());
//        wxLoginResponse.setPrizeName(allPrizeName);
//
//        return CommonResponse.success("小程序登录",wxLoginResponse);
    }
}
