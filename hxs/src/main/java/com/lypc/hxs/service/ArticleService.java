package com.lypc.hxs.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lypc.hxs.pojo.domain.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 文章信息表 服务类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
public interface ArticleService extends IService<Article> {

    public List<Article> getArticlesOfCurrentUser(Integer userId);

    public Page<Article> getRecentArticles(Integer current, Integer size);

    public Page<Article> getArticlesByHits(Integer current, Integer size);

    public List<Article> getArticlesByTitle(String title);

    public List<Article> getNoExamineArticles();

    public void examineArticle(Integer articleId);
}
