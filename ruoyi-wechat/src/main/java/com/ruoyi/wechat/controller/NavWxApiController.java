package com.ruoyi.wechat.controller;

import com.ruoyi.action.domain.SysAcNavbar;
import com.ruoyi.action.domain.bo.SysAcNavbarBo;
import com.ruoyi.action.domain.vo.SysAcNavbarVo;
import com.ruoyi.action.service.ISysAcNavbarService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.wechat.utils.RedisCache;
import com.ruoyi.wechat.utils.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @Description: wx小程序 api控制类
 */
@RestController
@RequestMapping("/api/miniWechat")
public class NavWxApiController extends BaseController {

    @Autowired
    private ISysAcNavbarService sysAcNavbarService;

    @GetMapping("/getNav")
    public R<List<SysAcNavbarVo>> getNavList(SysAcNavbarBo bo){
        return R.ok(sysAcNavbarService.queryList(bo));
    }


}
