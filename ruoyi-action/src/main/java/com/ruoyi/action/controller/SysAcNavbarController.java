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
import com.ruoyi.action.domain.vo.SysAcNavbarVo;
import com.ruoyi.action.domain.bo.SysAcNavbarBo;
import com.ruoyi.action.service.ISysAcNavbarService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 导航信息
 *
 * @author huangxing
 * @date 2024-03-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/action/navbar")
public class SysAcNavbarController extends BaseController {

    private final ISysAcNavbarService iSysAcNavbarService;

    /**
     * 查询导航信息列表
     */
    @SaCheckPermission("action:navbar:list")
    @GetMapping("/list")
    public TableDataInfo<SysAcNavbarVo> list(SysAcNavbarBo bo, PageQuery pageQuery) {
        return iSysAcNavbarService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出导航信息列表
     */
    @SaCheckPermission("action:navbar:export")
    @Log(title = "导航信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysAcNavbarBo bo, HttpServletResponse response) {
        List<SysAcNavbarVo> list = iSysAcNavbarService.queryList(bo);
        ExcelUtil.exportExcel(list, "导航信息", SysAcNavbarVo.class, response);
    }

    /**
     * 获取导航信息详细信息
     *
     * @param navId 主键
     */
    @SaCheckPermission("action:navbar:query")
    @GetMapping("/{navId}")
    public R<SysAcNavbarVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long navId) {
        return R.ok(iSysAcNavbarService.queryById(navId));
    }

    /**
     * 新增导航信息
     */
    @SaCheckPermission("action:navbar:add")
    @Log(title = "导航信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysAcNavbarBo bo) {
        return toAjax(iSysAcNavbarService.insertByBo(bo));
    }

    /**
     * 修改导航信息
     */
    @SaCheckPermission("action:navbar:edit")
    @Log(title = "导航信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysAcNavbarBo bo) {
        return toAjax(iSysAcNavbarService.updateByBo(bo));
    }

    /**
     * 删除导航信息
     *
     * @param navIds 主键串
     */
    @SaCheckPermission("action:navbar:remove")
    @Log(title = "导航信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{navIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] navIds) {
        return toAjax(iSysAcNavbarService.deleteWithValidByIds(Arrays.asList(navIds), true));
    }
}
