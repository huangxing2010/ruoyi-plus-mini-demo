package com.ruoyi.action.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.action.domain.SysAcCategory;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 文章信息视图对象 sys_ac_article
 *
 * @author huangxing
 * @date 2024-03-14
 */
@Data
@ExcelIgnoreUnannotated
public class SysAcArticleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @ExcelProperty(value = "文章id")
    private Long articleId;

    /**
     * 分类id
     */
    @ExcelProperty(value = "分类id")
    private Long categoryId;

    /**
     * 文章标题
     */
    @ExcelProperty(value = "文章标题")
    private String articleTitle;

    /**
     * 副标题
     */
    @ExcelProperty(value = "副标题")
    private String subhead;

    /**
     * 来源
     */
    @ExcelProperty(value = "来源")
    private String source;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String description;

    /**
     * 缩略图
     */
    @ExcelProperty(value = "缩略图")
    private String thumbnail;

    /**
     * 访问量
     */
    @ExcelProperty(value = "访问量")
    private Integer artHits;

    /**
     * 内容
     */
    @ExcelProperty(value = "内容")
    private String content;

    /**
     * 类型
     */
    @ExcelProperty(value = "类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_notice_type")
    private String artType;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_notice_status")
    private String artStatus;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 创建者
     */
    @ExcelProperty(value = "创建者")
    private String createBy;

    /**
     * 创建日期
     */
    @ExcelProperty(value = "创建日期")
    private Date createTime;

    /**
     * 分类信息
     */
    private SysAcCategory acCategory;


}
