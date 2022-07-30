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
 * 
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Getter
@Setter
@TableName("image")
@ApiModel(value = "Image对象", description = "")
public class Image {

    @ApiModelProperty("主键")
    @TableId(value = "image_id", type = IdType.AUTO)
    private Integer imageId;

    @ApiModelProperty("图片url")
    @TableField("image_url")
    private String imageUrl;

    @ApiModelProperty("图片名称")
    @TableField("image_name")
    private String imageName;

    @ApiModelProperty("上传用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
