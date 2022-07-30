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
 * 教师表
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Getter
@Setter
@TableName("teacher")
@ApiModel(value = "Teacher对象", description = "教师表")
public class Teacher {

    @ApiModelProperty("主键id")
    @TableId(value = "teacher_id", type = IdType.AUTO)
    private Integer teacherId;

    @ApiModelProperty("姓名")
    @TableField("teacher_name")
    private String teacherName;

    @ApiModelProperty("身份卡")
    @TableField("teacher_card_id")
    private Integer teacherCardId;

    @ApiModelProperty("所属单位")
    @TableField("teacher_souce")
    private String teacherSouce;

    @ApiModelProperty("所属团队")
    @TableField("teacher_team")
    private String teacherTeam;

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

    @ApiModelProperty("录入管理员id")
    @TableField("admin_id")
    private Integer adminId;

}
