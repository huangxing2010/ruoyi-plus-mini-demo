package com.ruoyi.action.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 内容分类业务对象 sys_ac_category
 *
 * @author huangxing
 * @date 2024-03-14
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SysAcCategoryBo extends TreeEntity<SysAcCategoryBo> {

    /**
     * id
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String categoryName;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
