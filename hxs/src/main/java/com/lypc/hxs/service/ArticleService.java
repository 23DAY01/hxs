package com.lypc.hxs.service;

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

}
