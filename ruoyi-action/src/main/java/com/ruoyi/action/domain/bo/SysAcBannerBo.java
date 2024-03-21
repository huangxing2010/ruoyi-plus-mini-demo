package com.ruoyi.action.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 轮播图业务对象 sys_ac_banner
 *
 * @author huangxing
 * @date 2024-03-14
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SysAcBannerBo extends BaseEntity {

    /**
     * 幻灯id
     */
    @NotNull(message = "幻灯id不能为空", groups = { EditGroup.class })
    private Long bannerId;

    /**
     * 幻灯名称
     */
    @NotBlank(message = "幻灯名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bannerName;

    /**
     * 连接地址
     */
    @NotBlank(message = "连接地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bannerUrl;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空", groups = { AddGroup.class, EditGroup.class })
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
