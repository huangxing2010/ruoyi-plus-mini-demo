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
import com.ruoyi.action.domain.vo.SysAcCategoryVo;
import com.ruoyi.action.domain.bo.SysAcCategoryBo;
import com.ruoyi.action.service.ISysAcCategoryService;

/**
 * 内容分类
 *
 * @author huangxing
 * @date 2024-03-14
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/action/category")
public class SysAcCategoryController extends BaseController {

    private final ISysAcCategoryService iSysAcCategoryService;

    /**
     * 查询内容分类列表
     */
    @SaCheckPermission("action:category:list")
    @GetMapping("/list")
    public R<List<SysAcCategoryVo>> list(SysAcCategoryBo bo) {
        List<SysAcCategoryVo> list = iSysAcCategoryService.queryList(bo);
        return R.ok(list);
    }

    /**
     * 导出内容分类列表
     */
    @SaCheckPermission("action:category:export")
    @Log(title = "内容分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysAcCategoryBo bo, HttpServletResponse response) {
        List<SysAcCategoryVo> list = iSysAcCategoryService.queryList(bo);
        ExcelUtil.exportExcel(list, "内容分类", SysAcCategoryVo.class, response);
    }

    /**
     * 获取内容分类详细信息
     *
     * @param categoryId 主键
     */
    @SaCheckPermission("action:category:query")
    @GetMapping("/{categoryId}")
    public R<SysAcCategoryVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long categoryId) {
        return R.ok(iSysAcCategoryService.queryById(categoryId));
    }

    /**
     * 新增内容分类
     */
    @SaCheckPermission("action:category:add")
    @Log(title = "内容分类", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysAcCategoryBo bo) {
        return toAjax(iSysAcCategoryService.insertByBo(bo));
    }

    /**
     * 修改内容分类
     */
    @SaCheckPermission("action:category:edit")
    @Log(title = "内容分类", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysAcCategoryBo bo) {
        return toAjax(iSysAcCategoryService.updateByBo(bo));
    }

    /**
     * 删除内容分类
     *
     * @param categoryIds 主键串
     */
    @SaCheckPermission("action:category:remove")
    @Log(title = "内容分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] categoryIds) {
        return toAjax(iSysAcCategoryService.deleteWithValidByIds(Arrays.asList(categoryIds), true));
    }
}
