package com.ruoyi.action.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.action.domain.bo.SysAcBannerBo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.action.domain.bo.SysAcArticleBo;
import com.ruoyi.action.domain.vo.SysAcArticleVo;
import com.ruoyi.action.domain.SysAcArticle;
import com.ruoyi.action.mapper.SysAcArticleMapper;
import com.ruoyi.action.service.ISysAcArticleService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 文章信息Service业务层处理
 *
 * @author huangxing
 * @date 2024-03-14
 */
@RequiredArgsConstructor
@Service
public class SysAcArticleServiceImpl implements ISysAcArticleService {

    private final SysAcArticleMapper baseMapper;

    /**
     * 查询文章信息
     */
    @Override
    public SysAcArticleVo queryById(Long articleId){
        return baseMapper.selectVoById(articleId);
    }

    /**
     * 查询文章信息列表
     */
    @Override
    public TableDataInfo<SysAcArticleVo> queryPageList(SysAcArticleBo bo, PageQuery pageQuery) {

        // 假设我们有一个整型数组
//        int[] numbers = new int[50];
        // 初始化或填充数组...

        // 遍历数组
   /*     for (int i = 0; i < 50; i++) {
            SysAcArticle sysAcArticle = new SysAcArticle();
            sysAcArticle.setArticleTitle("第" + i + "条数据，测试数据");
            sysAcArticle.setCategoryId(1768305888276471810L);
            sysAcArticle.setSource("网络");
            sysAcArticle.setContent("测试测试是佛啊佛山佛号佛法后绥芬河市的粉红色佛山佛山东方红");

            baseMapper.insert(sysAcArticle);

            System.out.println("6665656" + i);
        }
        System.out.println("bo====" + bo);*/




        LambdaQueryWrapper<SysAcArticle> lqw = buildQueryWrapper(bo);
        Page<SysAcArticleVo> result = baseMapper.selectArticlePageList(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询文章信息列表
     */
    @Override
    public List<SysAcArticleVo> queryList(SysAcArticleBo bo) {
        LambdaQueryWrapper<SysAcArticle> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    @Override
    public List<SysAcArticleVo> wxQueryList(SysAcArticleBo bo){
        System.out.println("bo====" + bo);
        LambdaQueryWrapper<SysAcArticle> lqw = buildQueryWrapper(bo);
        lqw.eq(StringUtils.isNotBlank(bo.getArtType()), SysAcArticle::getArtType, bo.getArtType());
        lqw.last("LIMIT 6");
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysAcArticle> buildQueryWrapper(SysAcArticleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysAcArticle> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCategoryId() != null, SysAcArticle::getCategoryId, bo.getCategoryId());
        lqw.eq(StringUtils.isNotBlank(bo.getArticleTitle()), SysAcArticle::getArticleTitle, bo.getArticleTitle());
        lqw.orderByDesc(SysAcArticle::getCreateTime);
        return lqw;
    }

    /**
     * 新增文章信息
     */
    @Override
    public Boolean insertByBo(SysAcArticleBo bo) {
        SysAcArticle add = BeanUtil.toBean(bo, SysAcArticle.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setArticleId(add.getArticleId());
        }
        return flag;
    }

    /**
     * 修改文章信息
     */
    @Override
    public Boolean updateByBo(SysAcArticleBo bo) {
        SysAcArticle update = BeanUtil.toBean(bo, SysAcArticle.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysAcArticle entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除文章信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
