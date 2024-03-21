package com.ruoyi.wechat.utils;

import cn.hutool.http.HttpUtil;
import com.ruoyi.common.utils.http.HttpUtils;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

public class WxUtil {

    /**
     * 获取AccessToken
     * @param APPID
     * @param SECRET
     * @return
     */
    public static String obtainAccessToken(String APPID,String SECRET){
        String tokenData = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+SECRET;
        // 返回的用户信息json字符串
        String resp = HttpUtils.sendGet(tokenData);
        JSONObject jsonObject = JSON.parseObject(resp);
        return String.valueOf(jsonObject.get("access_token"));
    }

    /**
     * 获取手机号码
     * @param accessToken
     * @param code
     * @return
     */
    public static String getPhoneNum(Object accessToken,String code){
        String path = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token="+accessToken;
        //封装参数
        JSONObject jsonData = new JSONObject();
        jsonData.put("code", code);
        // 返回的用户信息json字符串，此处调用Hutool工具类
        String resp = HttpUtil.post(path, jsonData.toJSONString());
        JSONObject jsonObject = JSON.parseObject(resp);
        JSONObject phoneInfo = JSON.parseObject(jsonObject.get("phone_info").toString());
        return String.valueOf(phoneInfo.get("phoneNumber"));
    }
}
