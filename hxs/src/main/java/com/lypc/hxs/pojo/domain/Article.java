package com.lypc.hxs.pojo.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;

/**
 * <p>
 * 文章信息表
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Getter
@Setter
@TableName("article")
@ApiModel(value = "Article对象", description = "文章信息表")
public class Article {

    @ApiModelProperty("主键")
    @TableId(value = "article_id", type = IdType.AUTO)
    private Integer articleId;

    @ApiModelProperty("文章标题")
    @TableField("article_title")
    private String articleTitle;

    @ApiModelProperty("文章缩略")
    @TableField("article_slug")
    private String articleSlug;

    @ApiModelProperty("文章内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("分类id")
    @TableField("category_id")
    private Integer categoryId;

    @ApiModelProperty("0-草稿 1-发布")
    @TableField("article_status")
    private Integer articleStatus;

    @ApiModelProperty("点击量")
    @TableField("article_hits")
    private Integer articleHits;

    @ApiModelProperty("0-允许评论 1-不允许评论")
    @TableField("enable_comment")
    private Integer enableComment;

    @ApiModelProperty("是否删除 0=否 1=是")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty("添加时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("所属用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("文章图片")
    @TableField("article_image")
    private String articleImage;

    @ApiModelProperty("是否通过审核 0-未审核")
    @TableField("Examined")
    private Integer Examined;


}
