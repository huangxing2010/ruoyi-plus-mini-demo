package com.ruoyi.wechat.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.wechat.utils.RedisCache;
import com.ruoyi.wechat.utils.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

import java.util.HashMap;
import java.util.Map;


/**
 * @Description: wx小程序 api控制类
 */
@RestController
@RequestMapping("/api/miniWechat")
public class MiniWxApiController extends BaseController {
    @Autowired
    private RedisCache redisCache;


    @Value("${wechat.mini.appId}")
    private String APPID;
    @Value("${wechat.mini.secret}")
    private String SECRET;
    /**
     * 获取用户手机号码
     */
    @GetMapping("/getPhoneNum")
    public R getPhoneNum(String code) {
        Map<String, Object> ajax = new HashMap<>();
        Object token = WxUtil.obtainAccessToken(APPID, SECRET);
        String phoneNum = WxUtil.getPhoneNum(token, code);
        redisCache.setCacheObject(Constants.WX_PHONE_NUM_KEY + phoneNum, code, Constants.WX_PHONE_CODE_EXPIRATION, TimeUnit.MINUTES);
        ajax.put("phoneNum", phoneNum);
        return R.ok(ajax);
    }


}
