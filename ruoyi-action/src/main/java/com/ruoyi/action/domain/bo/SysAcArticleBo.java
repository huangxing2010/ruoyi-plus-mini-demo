package com.ruoyi.action.domain.bo;

import com.ruoyi.action.domain.SysAcCategory;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文章信息业务对象 sys_ac_article
 *
 * @author huangxing
 * @date 2024-03-14
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SysAcArticleBo extends BaseEntity {

    /**
     * 文章id
     */
    @NotNull(message = "文章id不能为空", groups = { EditGroup.class })
    private Long articleId;

    /**
     * 分类id
     */
    @NotNull(message = "分类id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long categoryId;

    /**
     * 文章标题
     */
    @NotBlank(message = "文章标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String articleTitle;

    /**
     * 副标题
     */
    private String subhead;

    /**
     * 来源
     */
    @NotBlank(message = "来源不能为空", groups = { AddGroup.class, EditGroup.class })
    private String source;

    /**
     * 描述
     */
    private String description;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 访问量
     */
    private Integer artHits;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String content;

    /**
     * 类型
     */
    private String artType;

    /**
     * 状态
     */
    private String artStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 分类信息
     */
    private SysAcCategory acCategory;


}
