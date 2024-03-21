package com.ruoyi.action.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 轮播图视图对象 sys_ac_banner
 *
 * @author huangxing
 * @date 2024-03-14
 */
@Data
@ExcelIgnoreUnannotated
public class SysAcBannerVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 幻灯id
     */
    @ExcelProperty(value = "幻灯id")
    private Long bannerId;

    /**
     * 幻灯名称
     */
    @ExcelProperty(value = "幻灯名称")
    private String bannerName;

    /**
     * 连接地址
     */
    @ExcelProperty(value = "连接地址")
    private String bannerUrl;

    /**
     * 缩略图
     */
    @ExcelProperty(value = "缩略图")
    private String thumbnail;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Integer bannerSort;

    /**
     * 类型
     */
    @ExcelProperty(value = "类型")
    private String bannerType;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_show_hide")
    private String bannerStatus;


}
