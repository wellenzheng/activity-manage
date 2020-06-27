package com.example.activitymanage.service;

import org.springframework.stereotype.Service;

@Service
public class WXLoginService {
    public static String WX_LOGIN_URL="https://api.weixin.qq.com/sns/jscode2session";
    public static String LOGIN_APP_ID="wxc365774674af7d69";
    public static String LOGIN_APP_SECRET="7a3999c7179a803b09908f351b87beab";
    public static String LOGIN_GRANT_TYPE="authorization_code";
}
