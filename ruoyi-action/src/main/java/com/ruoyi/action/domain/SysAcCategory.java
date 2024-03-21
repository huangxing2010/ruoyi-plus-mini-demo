package com.ruoyi.action.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 内容分类对象 sys_ac_category
 *
 * @author huangxing
 * @date 2024-03-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_ac_category")
public class SysAcCategory extends TreeEntity<SysAcCategory> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "category_id")
    private Long categoryId;
    /**
     * 分类名称
     */
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
