package com.ruoyi.action.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.action.domain.SysAcArticle;
import com.ruoyi.action.domain.vo.SysAcArticleVo;
import com.ruoyi.common.annotation.DataColumn;
import com.ruoyi.common.annotation.DataPermission;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * 文章信息Mapper接口
 *
 * @author huangxing
 * @date 2024-03-14
 */
public interface SysAcArticleMapper extends BaseMapperPlus<SysAcArticleMapper, SysAcArticle, SysAcArticleVo> {

    Page<SysAcArticleVo> selectArticlePageList(@Param("page") Page<SysAcArticle> page, @Param(Constants.WRAPPER)Wrapper<SysAcArticle> queryWrapper);

}
