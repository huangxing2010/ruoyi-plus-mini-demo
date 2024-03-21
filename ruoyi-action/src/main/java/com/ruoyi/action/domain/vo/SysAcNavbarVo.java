package com.ruoyi.action.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 导航信息视图对象 sys_ac_navbar
 *
 * @author huangxing
 * @date 2024-03-05
 */
@Data
@ExcelIgnoreUnannotated
public class SysAcNavbarVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 导航id
     */
    @ExcelProperty(value = "导航id")
    private Long navId;

    /**
     * 导航名称
     */
    @ExcelProperty(value = "导航名称")
    private String navName;

    /**
     * 导航图标
     */
    @ExcelProperty(value = "导航图标")
    private String navIcon;

    /**
     * 连接地址
     */
    @ExcelProperty(value = "连接地址")
    private String navUrl;

    /**
     * 缩略图
     */
    @ExcelProperty(value = "缩略图")
    private String thumbnail;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Integer navSort;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String navStatus;

    /**
     * 创建日期
     */
    @ExcelProperty(value = "创建日期")
    private Date createTime;


}
