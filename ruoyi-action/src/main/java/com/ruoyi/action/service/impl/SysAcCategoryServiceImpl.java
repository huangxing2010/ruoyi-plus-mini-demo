package com.ruoyi.action.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.action.domain.bo.SysAcCategoryBo;
import com.ruoyi.action.domain.vo.SysAcCategoryVo;
import com.ruoyi.action.domain.SysAcCategory;
import com.ruoyi.action.mapper.SysAcCategoryMapper;
import com.ruoyi.action.service.ISysAcCategoryService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 内容分类Service业务层处理
 *
 * @author huangxing
 * @date 2024-03-14
 */
@RequiredArgsConstructor
@Service
public class SysAcCategoryServiceImpl implements ISysAcCategoryService {

    private final SysAcCategoryMapper baseMapper;

    /**
     * 查询内容分类
     */
    @Override
    public SysAcCategoryVo queryById(Long categoryId){
        return baseMapper.selectVoById(categoryId);
    }


    /**
     * 查询内容分类列表
     */
    @Override
    public List<SysAcCategoryVo> queryList(SysAcCategoryBo bo) {
        LambdaQueryWrapper<SysAcCategory> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysAcCategory> buildQueryWrapper(SysAcCategoryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysAcCategory> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getParentId() != null, SysAcCategory::getParentId, bo.getParentId());
        lqw.like(StringUtils.isNotBlank(bo.getCategoryName()), SysAcCategory::getCategoryName, bo.getCategoryName());
        return lqw;
    }

    /**
     * 新增内容分类
     */
    @Override
    public Boolean insertByBo(SysAcCategoryBo bo) {
        SysAcCategory add = BeanUtil.toBean(bo, SysAcCategory.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCategoryId(add.getCategoryId());
        }
        return flag;
    }

    /**
     * 修改内容分类
     */
    @Override
    public Boolean updateByBo(SysAcCategoryBo bo) {
        SysAcCategory update = BeanUtil.toBean(bo, SysAcCategory.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysAcCategory entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除内容分类
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
