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
import com.ruoyi.action.domain.bo.SysAcNavbarBo;
import com.ruoyi.action.domain.vo.SysAcNavbarVo;
import com.ruoyi.action.domain.SysAcNavbar;
import com.ruoyi.action.mapper.SysAcNavbarMapper;
import com.ruoyi.action.service.ISysAcNavbarService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 导航信息Service业务层处理
 *
 * @author huangxing
 * @date 2024-03-05
 */
@RequiredArgsConstructor
@Service
public class SysAcNavbarServiceImpl implements ISysAcNavbarService {

    private final SysAcNavbarMapper baseMapper;

    /**
     * 查询导航信息
     */
    @Override
    public SysAcNavbarVo queryById(Long navId){
        return baseMapper.selectVoById(navId);
    }

    /**
     * 查询导航信息列表
     */
    @Override
    public TableDataInfo<SysAcNavbarVo> queryPageList(SysAcNavbarBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysAcNavbar> lqw = buildQueryWrapper(bo);
        Page<SysAcNavbarVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询导航信息列表
     */
    @Override
    public List<SysAcNavbarVo> queryList(SysAcNavbarBo bo) {
        LambdaQueryWrapper<SysAcNavbar> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysAcNavbar> buildQueryWrapper(SysAcNavbarBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysAcNavbar> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getNavName()), SysAcNavbar::getNavName, bo.getNavName());
        lqw.eq(StringUtils.isNotBlank(bo.getNavStatus()), SysAcNavbar::getNavStatus, bo.getNavStatus());
        return lqw;
    }

    /**
     * 新增导航信息
     */
    @Override
    public Boolean insertByBo(SysAcNavbarBo bo) {
        SysAcNavbar add = BeanUtil.toBean(bo, SysAcNavbar.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setNavId(add.getNavId());
        }
        return flag;
    }

    /**
     * 修改导航信息
     */
    @Override
    public Boolean updateByBo(SysAcNavbarBo bo) {
        SysAcNavbar update = BeanUtil.toBean(bo, SysAcNavbar.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysAcNavbar entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除导航信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
