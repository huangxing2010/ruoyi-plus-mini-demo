package com.ruoyi.action.service;

import com.ruoyi.action.domain.SysAcArticle;
import com.ruoyi.action.domain.bo.SysAcBannerBo;
import com.ruoyi.action.domain.vo.SysAcArticleVo;
import com.ruoyi.action.domain.bo.SysAcArticleBo;
import com.ruoyi.action.domain.vo.SysAcBannerVo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 文章信息Service接口
 *
 * @author huangxing
 * @date 2024-03-14
 */
public interface ISysAcArticleService {

    /**
     * 查询文章信息
     */
    SysAcArticleVo queryById(Long articleId);

    /**
     * 查询文章信息列表
     */
    TableDataInfo<SysAcArticleVo> queryPageList(SysAcArticleBo bo, PageQuery pageQuery);

    /**
     * 查询文章信息列表
     */
    List<SysAcArticleVo> queryList(SysAcArticleBo bo);

    /**
     * 小程序首页限量查询
     */
    List<SysAcArticleVo> wxQueryList(SysAcArticleBo bo);

    /**
     * 新增文章信息
     */
    Boolean insertByBo(SysAcArticleBo bo);

    /**
     * 修改文章信息
     */
    Boolean updateByBo(SysAcArticleBo bo);

    /**
     * 校验并批量删除文章信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
