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
import com.ruoyi.action.domain.vo.SysAcBannerVo;
import com.ruoyi.action.domain.bo.SysAcBannerBo;
import com.ruoyi.action.service.ISysAcBannerService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 轮播图
 *
 * @author huangxing
 * @date 2024-03-14
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/action/banner")
public class SysAcBannerController extends BaseController {

    private final ISysAcBannerService iSysAcBannerService;

    /**
     * 查询轮播图列表
     */
    @SaCheckPermission("action:banner:list")
    @GetMapping("/list")
    public TableDataInfo<SysAcBannerVo> list(SysAcBannerBo bo, PageQuery pageQuery) {
        return iSysAcBannerService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出轮播图列表
     */
    @SaCheckPermission("action:banner:export")
    @Log(title = "轮播图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysAcBannerBo bo, HttpServletResponse response) {
        List<SysAcBannerVo> list = iSysAcBannerService.queryList(bo);
        ExcelUtil.exportExcel(list, "轮播图", SysAcBannerVo.class, response);
    }

    /**
     * 获取轮播图详细信息
     *
     * @param bannerId 主键
     */
    @SaCheckPermission("action:banner:query")
    @GetMapping("/{bannerId}")
    public R<SysAcBannerVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long bannerId) {
        return R.ok(iSysAcBannerService.queryById(bannerId));
    }

    /**
     * 新增轮播图
     */
    @SaCheckPermission("action:banner:add")
    @Log(title = "轮播图", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysAcBannerBo bo) {
        return toAjax(iSysAcBannerService.insertByBo(bo));
    }

    /**
     * 修改轮播图
     */
    @SaCheckPermission("action:banner:edit")
    @Log(title = "轮播图", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysAcBannerBo bo) {
        return toAjax(iSysAcBannerService.updateByBo(bo));
    }

    /**
     * 删除轮播图
     *
     * @param bannerIds 主键串
     */
    @SaCheckPermission("action:banner:remove")
    @Log(title = "轮播图", businessType = BusinessType.DELETE)
    @DeleteMapping("/{bannerIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] bannerIds) {
        return toAjax(iSysAcBannerService.deleteWithValidByIds(Arrays.asList(bannerIds), true));
    }
}
