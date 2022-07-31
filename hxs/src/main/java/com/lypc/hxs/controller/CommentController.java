package com.lypc.hxs.controller;

import com.lypc.hxs.pojo.domain.Comment;
import com.lypc.hxs.service.ArticleService;
import com.lypc.hxs.service.CommentService;
import com.lypc.hxs.utils.AuthUtil;
import com.lypc.hxs.utils.ResponseAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.NavigableMap;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Api(tags = "评论controller")
@RestController
@RequestMapping("/user/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    /**
     * 发表评论
     *
     * @param comment
     * @param request
     * @return
     */
    @ApiOperation("发表评论")
    @PostMapping("/publishComment")
    public ResponseAPI<?> publishComment(
            @ApiParam(name = "comment", value = "comment", required = true)
            @RequestBody
                    Comment comment, HttpServletRequest request) {

        Integer userId = AuthUtil.getCurrentUserId(request);
        comment.setUserId(userId);

        commentService.save(comment);
        return ResponseAPI.success();
    }

    /**
     * 修改评论
     *
     * @param comment
     * @return
     */
    @ApiOperation("修改评论")
    @PostMapping("/modifyComment")
    public ResponseAPI<?> modifyComment(
            @ApiParam(name = "comment", value = "comment", required = true)
            @RequestBody
                    Comment comment) {
        commentService.updateById(comment);
        return ResponseAPI.success();
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @ApiOperation("删除评论")
    @GetMapping("/delComment")
    public ResponseAPI<?> delComment(
            @ApiParam(name = "commentId", value = "评论id", required = true)
            @RequestParam(name = "commentId")
                    Integer commentId) {
        commentService.removeById(commentId);
        return ResponseAPI.success();
    }

    /**
     * 获取当前文章的所有评论
     * @param articleId
     * @return
     */
    @ApiOperation("获取当前文章的所有评论")
    @GetMapping("/getCommentByArticleId")
    public ResponseAPI<?> getCommentByArticleId(
            @ApiParam(name = "articleId", value = "文章id", required = true)
            @RequestParam(name = "articleId")
                    Integer articleId) {

        List<Comment> comments = commentService.getCommentByArticleId(articleId);
        return ResponseAPI.success(comments);
    }
}
