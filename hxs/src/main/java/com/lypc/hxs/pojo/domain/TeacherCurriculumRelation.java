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
 * @since 2022-08-05
 */
@Getter
@Setter
@TableName("teacher_curriculum_relation")
@ApiModel(value = "TeacherCurriculumRelation对象", description = "")
public class TeacherCurriculumRelation {

    @ApiModelProperty("主键id")
    @TableId(value = "relation_id", type = IdType.AUTO)
    private Integer relationId;

    @ApiModelProperty("教师id")
    @TableField("teacher_id")
    private Integer teacherId;

    @ApiModelProperty("课程id")
    @TableField("curriculum_id")
    private Integer curriculumId;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
