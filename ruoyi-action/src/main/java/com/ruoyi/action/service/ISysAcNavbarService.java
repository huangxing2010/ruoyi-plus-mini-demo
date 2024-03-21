package com.ruoyi.action.service;

import com.ruoyi.action.domain.SysAcNavbar;
import com.ruoyi.action.domain.vo.SysAcNavbarVo;
import com.ruoyi.action.domain.bo.SysAcNavbarBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 导航信息Service接口
 *
 * @author huangxing
 * @date 2024-03-05
 */
public interface ISysAcNavbarService {

    /**
     * 查询导航信息
     */
    SysAcNavbarVo queryById(Long navId);

    /**
     * 查询导航信息列表
     */
    TableDataInfo<SysAcNavbarVo> queryPageList(SysAcNavbarBo bo, PageQuery pageQuery);

    /**
     * 查询导航信息列表
     */
    List<SysAcNavbarVo> queryList(SysAcNavbarBo bo);

    /**
     * 新增导航信息
     */
    Boolean insertByBo(SysAcNavbarBo bo);

    /**
     * 修改导航信息
     */
    Boolean updateByBo(SysAcNavbarBo bo);

    /**
     * 校验并批量删除导航信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
