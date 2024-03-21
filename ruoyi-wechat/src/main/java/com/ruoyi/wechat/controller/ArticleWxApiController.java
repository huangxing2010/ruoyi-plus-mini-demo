package com.ruoyi.wechat.controller;

import com.ruoyi.action.domain.SysAcArticle;
import com.ruoyi.action.domain.bo.SysAcArticleBo;
import com.ruoyi.action.domain.bo.SysAcBannerBo;
import com.ruoyi.action.domain.vo.SysAcArticleVo;
import com.ruoyi.action.service.ISysAcArticleService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 *  获取banner信息
 * @Description: wx小程序 api控制类
 */
@RestController
@RequestMapping("/api/miniWechat")
public class ArticleWxApiController extends BaseController {

    @Autowired
    private ISysAcArticleService sysAcArticleService;

    /**
     * 获取推荐文章列表
     * @param bo
     * @return
     */
    @GetMapping("/getArticleList")
    public R<List<SysAcArticleVo>> getArticleList(SysAcArticleBo bo){
        return R.ok(sysAcArticleService.wxQueryList(bo));
    }

    /**
     * 获取文章列表
     * @param bo
     * @param pageQuery
     * @return
     */
    @GetMapping("/articlePageList")
    public TableDataInfo<SysAcArticleVo> list(SysAcArticleBo bo, PageQuery pageQuery) {
        return sysAcArticleService.queryPageList(bo, pageQuery);
    }

/*
    @GetMapping("/getArticleContent")
    public R<SysAcArticleVo> getArticleContent(Long articleId){
        return R.ok(sysAcArticleService.queryById(articleId));
    }
*/

    /**
     * 获取文章详情
     * @param articleId
     * @return
     */
    @GetMapping("/getArticleContent")
    public R<SysAcArticleVo> getArticleContent(@RequestParam("articleId") Long articleId) {
        SysAcArticleVo sysAcArticleInfo = sysAcArticleService.queryById(articleId);
        //更新浏览量
        SysAcArticleBo sysAcArticleBo = new SysAcArticleBo();
        sysAcArticleBo.setArticleId(articleId);
        sysAcArticleBo.setArtHits(sysAcArticleInfo.getArtHits() + 1);
        sysAcArticleService.updateByBo(sysAcArticleBo);
        return R.ok(sysAcArticleInfo);
    }


}
