package com.lypc.hxs.service.impl;

import com.lypc.hxs.pojo.domain.Comment;
import com.lypc.hxs.mapper.CommentMapper;
import com.lypc.hxs.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
