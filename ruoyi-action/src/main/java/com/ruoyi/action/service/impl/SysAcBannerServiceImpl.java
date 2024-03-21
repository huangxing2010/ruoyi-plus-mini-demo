package com.ruoyi.action.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.action.domain.bo.SysAcBannerBo;
import com.ruoyi.action.domain.vo.SysAcBannerVo;
import com.ruoyi.action.domain.SysAcBanner;
import com.ruoyi.action.mapper.SysAcBannerMapper;
import com.ruoyi.action.service.ISysAcBannerService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 轮播图Service业务层处理
 *
 * @author huangxing
 * @date 2024-03-14
 */
@RequiredArgsConstructor
@Service
public class SysAcBannerServiceImpl implements ISysAcBannerService {

    private final SysAcBannerMapper baseMapper;

    /**
     * 查询轮播图
     */
    @Override
    public SysAcBannerVo queryById(Long bannerId){
        return baseMapper.selectVoById(bannerId);
    }

    /**
     * 查询轮播图列表
     */
    @Override
    public TableDataInfo<SysAcBannerVo> queryPageList(SysAcBannerBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysAcBanner> lqw = buildQueryWrapper(bo);
        Page<SysAcBannerVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询轮播图列表
     */
    @Override
    public List<SysAcBannerVo> queryList(SysAcBannerBo bo) {
        LambdaQueryWrapper<SysAcBanner> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysAcBanner> buildQueryWrapper(SysAcBannerBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysAcBanner> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getBannerName()), SysAcBanner::getBannerName, bo.getBannerName());
        lqw.eq(StringUtils.isNotBlank(bo.getBannerType()), SysAcBanner::getBannerType, bo.getBannerType());
        return lqw;
    }

    /**
     * 新增轮播图
     */
    @Override
    public Boolean insertByBo(SysAcBannerBo bo) {
        SysAcBanner add = BeanUtil.toBean(bo, SysAcBanner.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setBannerId(add.getBannerId());
        }
        return flag;
    }

    /**
     * 修改轮播图
     */
    @Override
    public Boolean updateByBo(SysAcBannerBo bo) {
        SysAcBanner update = BeanUtil.toBean(bo, SysAcBanner.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysAcBanner entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除轮播图
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
