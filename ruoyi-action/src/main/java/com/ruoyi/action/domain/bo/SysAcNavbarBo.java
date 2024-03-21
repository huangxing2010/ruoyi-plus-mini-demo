package com.ruoyi.action.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 导航信息业务对象 sys_ac_navbar
 *
 * @author huangxing
 * @date 2024-03-05
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SysAcNavbarBo extends BaseEntity {

    /**
     * 导航id
     */
    private Long navId;

    /**
     * 导航名称
     */
    @NotBlank(message = "导航名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String navName;

    /**
     * 导航图标
     */
    private String navIcon;

    /**
     * 连接地址
     */
    @NotBlank(message = "连接地址不能为空", groups = { AddGroup.class, EditGroup.class })
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
