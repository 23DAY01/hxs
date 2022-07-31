package com.lypc.hxs.service;

import com.lypc.hxs.pojo.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
public interface CommentService extends IService<Comment> {

    List<Comment> getCommentByArticleId(Integer articleId);


}
