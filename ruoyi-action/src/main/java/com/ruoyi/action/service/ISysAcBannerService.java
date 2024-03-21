package com.ruoyi.action.service;

import com.ruoyi.action.domain.SysAcBanner;
import com.ruoyi.action.domain.vo.SysAcBannerVo;
import com.ruoyi.action.domain.bo.SysAcBannerBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 轮播图Service接口
 *
 * @author huangxing
 * @date 2024-03-14
 */
public interface ISysAcBannerService {

    /**
     * 查询轮播图
     */
    SysAcBannerVo queryById(Long bannerId);

    /**
     * 查询轮播图列表
     */
    TableDataInfo<SysAcBannerVo> queryPageList(SysAcBannerBo bo, PageQuery pageQuery);

    /**
     * 查询轮播图列表
     */
    List<SysAcBannerVo> queryList(SysAcBannerBo bo);

    /**
     * 新增轮播图
     */
    Boolean insertByBo(SysAcBannerBo bo);

    /**
     * 修改轮播图
     */
    Boolean updateByBo(SysAcBannerBo bo);

    /**
     * 校验并批量删除轮播图信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
