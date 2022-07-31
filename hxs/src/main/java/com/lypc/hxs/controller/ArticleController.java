package com.lypc.hxs.controller;

import com.lypc.hxs.constant.StatusCode;
import com.lypc.hxs.pojo.domain.Article;
import com.lypc.hxs.pojo.domain.User;
import com.lypc.hxs.service.ArticleService;
import com.lypc.hxs.utils.AuthUtil;
import com.lypc.hxs.utils.ResponseAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 文章信息表 前端控制器
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-27
 */
@Api("文章controller")
@RestController
@RequestMapping("/user/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    /**
     * 用户添加文章
     *
     * @param article
     * @param request
     * @return
     */
    @ApiOperation(value = "当前用户添加文章")
    @PostMapping("/addArticle")
    public ResponseAPI<?> addArticle(
            @ApiParam(name = "article", value = "article类", required = true)
            @RequestBody
                    Article article, HttpServletRequest request) {

        User user = AuthUtil.getLoginUser(request);
        assert user != null;
        article.setUserId(user.getUserId());
        articleService.save(article);
        return ResponseAPI.success();
    }


    /**
     * 通过id获取文章
     *
     * @param id
     * @return
     */
    @ApiOperation("通过id获取文章")
    @GetMapping("/getArticleById")
    public ResponseAPI<?> getArticleById(
            @ApiParam(name = "id", value = "文章的id", required = true)
            @RequestParam(name = "id")
                    Integer id) {
        Article article = articleService.getById(id);
        return ResponseAPI.success(article);
    }


    /**
     * 获取当前用户全部文章
     * @param request
     * @return
     */
    @ApiOperation("获取当前用户全部文章")
    @GetMapping("/getArticles")
    public ResponseAPI<?> getArticles(HttpServletRequest request) {
        Integer userId = AuthUtil.getCurrentUserId(request);
        return ResponseAPI.success(articleService.getArticlesOfCurrentUser(userId));
    }


    /**
     * @param article
     * @return
     */
    @ApiOperation("当前编辑文章")
    @PostMapping("/modifyArticle")
    public ResponseAPI<?> modifyArticle(
            @ApiParam(name = "article", value = "article类", required = true)
            @RequestBody
                    Article article) {
        articleService.updateById(article);
        return ResponseAPI.success();
    }


    /**
     * 当前用户删除文章
     * @param id
     * @return
     */
    @ApiOperation("当前用户删除文章")
    @GetMapping("/delArticle")
    public ResponseAPI<?> delArticle(
            @ApiParam(name = "id", value = "文章的id", required = true)
            @RequestParam(name = "id")
                    Integer id) {
        articleService.removeById(id);
        return ResponseAPI.success(StatusCode.SUCCESS.OK.getCode());
    }


}
