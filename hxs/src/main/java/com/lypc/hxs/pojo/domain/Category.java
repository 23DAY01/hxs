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

/**
 * <p>
 * 文章分类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Getter
@Setter
@TableName("category")
@ApiModel(value = "Category对象", description = "文章分类")
public class Category {

    @ApiModelProperty("主键id")
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    @ApiModelProperty("分类名称")
    @TableField("category_name")
    private String categoryName;

    @ApiModelProperty("分类图标")
    @TableField("category_icon")
    private String categoryIcon;

    @ApiModelProperty("所属内容个数")
    @TableField("category_count")
    private Integer categoryCount;

    @ApiModelProperty("是否删除 0-未删除 1-已删除")
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


}
