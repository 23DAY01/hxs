package com.lypc.hxs.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lypc.hxs.pojo.domain.Article;
import com.lypc.hxs.service.ArticleService;
import com.lypc.hxs.utils.AuthUtil;
import com.lypc.hxs.utils.ResponseAPI;
import io.lettuce.core.Limit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Api(tags = "风采展示")
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ArticleService articleService;

    /**
     * 获取最近的10篇文章
     * @param current
     * @param size
     * @return
     */
    @ApiOperation("获取最近的10篇文章,不包括自己的")
    @GetMapping("/getRecentArticles")
    public ResponseAPI<?> getRecentArticles(
            @ApiParam(name = "current", value = "当前页")
            @RequestParam(name = "current", defaultValue = "1", required = false)
                    Integer current,
            @ApiParam(name = "size", value = "一页size条数据")
            @RequestParam(name = "size", defaultValue = "10", required = false)
                    Integer size) {

        //这边的默认数据都是根据page里面的默认数据来的

        //用了mybatis plus的分页插件
        Page<Article> articlePage = articleService.getRecentArticles(current, size);

        return ResponseAPI.success(articlePage);
    }

    /**
     * 根据热度获取文章
     * @param current
     * @param size
     * @return
     */
    @ApiOperation("根据热度获取文章,获取最近30天内点击度最高的文章")
    @GetMapping("/getArticleByHits")
    public ResponseAPI<?> getArticleByHits(
            @ApiParam(name = "current", value = "当前页")
            @RequestParam(name = "current", defaultValue = "1", required = false)
                    Integer current,
            @ApiParam(name = "size", value = "一页size条数据")
            @RequestParam(name = "size", defaultValue = "10", required = false)
                    Integer size) {

        Page<Article> articlePage = articleService.getArticlesByHits(current, size);

        return ResponseAPI.success(articlePage);
    }

    /**
     * 根据文章标题搜索文章
     * @param title
     * @return
     */
    @ApiOperation("根据文章标题搜索文章")
    @GetMapping("/getArticlesByTitle")
    public ResponseAPI<?> getArticleByTitle(
            @ApiParam(name = "title", value = "文章标题", required = true)
            @RequestParam(name = "title")
                    String title) {
        List<Article> articles = articleService.getArticlesByTitle(title);
        return ResponseAPI.success(articles);
    }




}
