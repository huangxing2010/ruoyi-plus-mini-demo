package com.ruoyi.action.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.core.validate.QueryGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.action.domain.vo.SysAcArticleVo;
import com.ruoyi.action.domain.bo.SysAcArticleBo;
import com.ruoyi.action.service.ISysAcArticleService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章信息
 *
 * @author huangxing
 * @date 2024-03-14
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/action/article")
public class SysAcArticleController extends BaseController {

    private final ISysAcArticleService iSysAcArticleService;

    /**
     * 查询文章信息列表
     */
    @SaCheckPermission("action:article:list")
    @GetMapping("/list")
    public TableDataInfo<SysAcArticleVo> list(SysAcArticleBo bo, PageQuery pageQuery) {
        return iSysAcArticleService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出文章信息列表
     */
    @SaCheckPermission("action:article:export")
    @Log(title = "文章信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysAcArticleBo bo, HttpServletResponse response) {
        List<SysAcArticleVo> list = iSysAcArticleService.queryList(bo);
        ExcelUtil.exportExcel(list, "文章信息", SysAcArticleVo.class, response);
    }

    /**
     * 获取文章信息详细信息
     *
     * @param articleId 主键
     */
    @SaCheckPermission("action:article:query")
    @GetMapping("/{articleId}")
    public R<SysAcArticleVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long articleId) {
        return R.ok(iSysAcArticleService.queryById(articleId));
    }

    /**
     * 新增文章信息
     */
    @SaCheckPermission("action:article:add")
    @Log(title = "文章信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysAcArticleBo bo) {
        return toAjax(iSysAcArticleService.insertByBo(bo));
    }

    /**
     * 修改文章信息
     */
    @SaCheckPermission("action:article:edit")
    @Log(title = "文章信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysAcArticleBo bo) {
        return toAjax(iSysAcArticleService.updateByBo(bo));
    }

    /**
     * 删除文章信息
     *
     * @param articleIds 主键串
     */
    @SaCheckPermission("action:article:remove")
    @Log(title = "文章信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{articleIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] articleIds) {
        return toAjax(iSysAcArticleService.deleteWithValidByIds(Arrays.asList(articleIds), true));
    }
}
