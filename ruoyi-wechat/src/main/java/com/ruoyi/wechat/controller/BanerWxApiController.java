package com.ruoyi.wechat.controller;

import com.ruoyi.action.domain.bo.SysAcBannerBo;
import com.ruoyi.action.domain.vo.SysAcBannerVo;
import com.ruoyi.action.service.ISysAcBannerService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 *  获取banner信息
 * @Description: wx小程序 api控制类
 */
@RestController
@RequestMapping("/api/miniWechat")
public class BanerWxApiController extends BaseController {

    @Autowired
    private ISysAcBannerService sysAcBannerService;

    @GetMapping("/getBanner")
    public R<List<SysAcBannerVo>> getBannerList(SysAcBannerBo bo){
        return R.ok(sysAcBannerService.queryList(bo));
    }


}
