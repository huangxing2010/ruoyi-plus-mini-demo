package com.ruoyi.action.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文章信息对象 sys_ac_article
 *
 * @author huangxing
 * @date 2024-03-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_ac_article")
public class SysAcArticle extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 文章id
     */
    @TableId(value = "article_id")
    private Long articleId;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 文章标题
     */
    private String articleTitle;
    /**
     * 副标题
     */
    private String subhead;
    /**
     * 来源
     */
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
    @TableField(exist = false)
    private SysAcCategory acCategory;

}
