package com.ruoyi.web.controller.mini;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.wechat.utils.RedisCache;
import com.ruoyi.common.enums.UserType;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class ThirdLoginController extends BaseController {

    @Autowired
    private ISysUserService userService;
    @Autowired
    private RedisCache redisCache;

    /**
     * 微信小程序注册/登录
     */
    @PostMapping("/weChatLogin")
    public R<Map<String, Object>> weChatLogin(HttpServletRequest request, @RequestBody LoginBody loginBody)
    {
        Map<String, Object> ajax = new HashMap<>();
        String msg = "登录成功";
        if (StringUtils.isEmpty(loginBody.getUsername()) || StringUtils.isEmpty(loginBody.getCode())){
            msg = "用户名/code不能为空";
            return R.fail(msg);
        }
        if (!redisCache.getCacheObject(Constants.WX_PHONE_NUM_KEY + loginBody.getUsername()).equals(loginBody.getCode())){
            msg = "code过期/错误";
            return R.fail(msg);
        }
        // 判断是否为新用户
        SysUser sysUser = userService.selectUserByUserName(loginBody.getUsername());
        // 不是新用户，创建用户
        if(sysUser == null){
            sysUser = new SysUser();
            sysUser.setUserName(loginBody.getUsername());
            sysUser.setNickName("微信用户");
            sysUser.setPhonenumber(loginBody.getUsername());
            sysUser.setPassword(BCrypt.hashpw(loginBody.getPassword()));
            //对应部门id
            sysUser.setDeptId(109L);
            //对应岗位id
            Long[] postIds = new Long[1];
            postIds[0] = 5L;
            sysUser.setPostIds(postIds);
            //对应角色id
            Long[] roleIds = new Long[1];
            roleIds[0] = 3L;
            sysUser.setRoleIds(roleIds);
            //  保存完用户后，还需要设置用户的角色，部门与岗位
            System.out.println("====user===" + sysUser);
            userService.insertUser(sysUser);
        }

        //实例化登录用户
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(sysUser.getUserName());
        loginUser.setUserType(UserType.APP_USER.getUserType());
        loginUser.setUserId(sysUser.getUserId());

        //登录
        LoginHelper.login(loginUser);

        //实例化用户，有用的信息返回
        SysUser ReSysUser = new SysUser();
        ReSysUser.setUserName(sysUser.getUserName());
        ReSysUser.setUserId(sysUser.getUserId());
        ReSysUser.setNickName(sysUser.getNickName());
        ReSysUser.setAvatar(sysUser.getAvatar());

        // 返回token
        String tokenValue = StpUtil.getTokenValue();
        ajax.put("loginUser", ReSysUser);
        ajax.put(Constants.TOKEN, tokenValue);
        return R.ok(msg,ajax);
    }


    @PostMapping("/updateProfile")
    public R updateProfile(@RequestBody SysUser user){

        userService.updateUser(user);
        System.out.println("user====" + user);
        return R.ok("okkkkk");
    }

    /**
     * 判断token是否过期
     * @param request
     * @param token
     * @return
     */
    @GetMapping("/profile/isExpiration")
    public R<Map<String, Object>> isExpiration(HttpServletRequest request,String token){
        long tokenTimeout = StpUtil.getTokenTimeout(token);
        Map<String, Object> ajax = new HashMap<>();
        if(tokenTimeout == -2){
            ajax.put("start", true);
        }else {
            ajax.put("start", false);
        }
        return R.ok(ajax);
    }
}
