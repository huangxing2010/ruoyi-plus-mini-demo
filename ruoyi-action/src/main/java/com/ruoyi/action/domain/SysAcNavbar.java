package com.ruoyi.action.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 导航信息对象 sys_ac_navbar
 *
 * @author huangxing
 * @date 2024-03-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_ac_navbar")
public class SysAcNavbar extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 导航id
     */
    @TableId(value = "nav_id")
    private Long navId;
    /**
     * 导航名称
     */
    private String navName;
    /**
     * 导航图标
     */
    private String navIcon;
    /**
     * 连接地址
     */
    private String navUrl;
    /**
     * 缩略图
     */
    private String thumbnail;
    /**
     * 排序
     */
    private Integer navSort;
    /**
     * 状态
     */
    private String navStatus;

}
