package com.ruoyi.action.service;

import com.ruoyi.action.domain.SysAcCategory;
import com.ruoyi.action.domain.vo.SysAcCategoryVo;
import com.ruoyi.action.domain.bo.SysAcCategoryBo;

import java.util.Collection;
import java.util.List;

/**
 * 内容分类Service接口
 *
 * @author huangxing
 * @date 2024-03-14
 */
public interface ISysAcCategoryService {

    /**
     * 查询内容分类
     */
    SysAcCategoryVo queryById(Long categoryId);


    /**
     * 查询内容分类列表
     */
    List<SysAcCategoryVo> queryList(SysAcCategoryBo bo);

    /**
     * 新增内容分类
     */
    Boolean insertByBo(SysAcCategoryBo bo);

    /**
     * 修改内容分类
     */
    Boolean updateByBo(SysAcCategoryBo bo);

    /**
     * 校验并批量删除内容分类信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
