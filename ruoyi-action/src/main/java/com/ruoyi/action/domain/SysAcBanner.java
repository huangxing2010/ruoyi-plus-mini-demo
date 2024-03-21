package com.ruoyi.action.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 轮播图对象 sys_ac_banner
 *
 * @author huangxing
 * @date 2024-03-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_ac_banner")
public class SysAcBanner extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 幻灯id
     */
    @TableId(value = "banner_id")
    private Long bannerId;
    /**
     * 幻灯名称
     */
    private String bannerName;
    /**
     * 连接地址
     */
    private String bannerUrl;
    /**
     * 缩略图
     */
    private String thumbnail;
    /**
     * 排序
     */
    private Integer bannerSort;
    /**
     * 类型
     */
    private String bannerType;
    /**
     * 状态
     */
    private String bannerStatus;

}
