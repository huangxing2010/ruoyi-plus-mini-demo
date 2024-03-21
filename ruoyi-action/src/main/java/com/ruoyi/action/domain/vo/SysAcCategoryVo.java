package com.ruoyi.action.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 内容分类视图对象 sys_ac_category
 *
 * @author huangxing
 * @date 2024-03-14
 */
@Data
@ExcelIgnoreUnannotated
public class SysAcCategoryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "父级分类id")
    private Long categoryId;

    /**
     * 父级分类id
     */
    @ExcelProperty(value = "父级分类id")
    private Long parentId;

    /**
     * 分类名称
     */
    @ExcelProperty(value = "分类名称")
    private String categoryName;

    /**
     * 缩略图
     */
    @ExcelProperty(value = "缩略图")
    private String thumbnail;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_show_hide")
    private String status;

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
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}
