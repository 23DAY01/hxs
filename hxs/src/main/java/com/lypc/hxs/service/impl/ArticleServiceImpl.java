package com.lypc.hxs.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lypc.hxs.pojo.domain.Article;
import com.lypc.hxs.mapper.ArticleMapper;
import com.lypc.hxs.pojo.domain.User;
import com.lypc.hxs.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lypc.hxs.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 文章信息表 服务实现类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public List<Article> getArticlesOfCurrentUser(Integer userId) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getUserId, userId);
        return articleMapper.selectList(wrapper);
    }

    @Override
    public Page<Article> getRecentArticles(Integer current, Integer size) {

        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        Page<Article> articlePage = new Page<>(current, size);

        //必须为发布状态
        wrapper.eq(Article::getArticleStatus, 1);

        //文章创建时间  说实话 p用没有
        wrapper.le(Article::getCreateTime, LocalDateTime.now());

        articleMapper.selectPage(articlePage,wrapper);

        return articlePage;
    }

    @Override
    public Page<Article> getArticlesByHits(Integer current, Integer size) {

        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        Page<Article> articlePage = new Page<>(current, size);

        //必须为发布状态
        wrapper.eq(Article::getArticleStatus, 1);

        //时间范围为前后30天
        LocalDateTime now = LocalDateTime.now();
        wrapper.le(Article::getCreateTime, now);
        wrapper.ge(Article::getCreateTime,now.minusDays(30L));

        //按照热度降序排
        wrapper.orderByDesc(Article::getArticleHits);

        articleMapper.selectPage(articlePage,wrapper);

        return articlePage;
    }

    @Override
    public List<Article> getArticlesByTitle(String title) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Article::getArticleTitle,title);
        return articleMapper.selectList(wrapper);
    }

    @Override
    public List<Article> getNoExamineArticles() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getExamined,0);
        List<Article> articles = articleMapper.selectList(wrapper);
        return articles;
    }

    @Override
    public void examineArticle(Integer articleId) {
        Article article = this.getById(articleId);
        article.setExamined(1);
    }


}
