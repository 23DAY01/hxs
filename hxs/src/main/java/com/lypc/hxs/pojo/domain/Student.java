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
 * 学生表
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Getter
@Setter
@TableName("student")
@ApiModel(value = "Student对象", description = "学生表")
public class Student {

    @ApiModelProperty("主键id")
    @TableId(value = "student_id", type = IdType.AUTO)
    private Integer studentId;

    @ApiModelProperty("姓名")
    @TableField("student_name")
    private String studentName;

    @ApiModelProperty("身份卡")
    @TableField("student_card_id")
    private Integer studentCardId;

    @ApiModelProperty("年龄")
    @TableField("student_age")
    private Integer studentAge;

    @ApiModelProperty("学校名称")
    @TableField("student_school")
    private String studentSchool;

    @ApiModelProperty("年级")
    @TableField("student_grade")
    private Integer studentGrade;

    @ApiModelProperty("父母是否外出务工")
    @TableField("parent_work")
    private Integer parentWork;

    @ApiModelProperty("成绩")
    @TableField("student_record")
    private Integer studentRecord;

    @ApiModelProperty("是否有手机")
    @TableField("student_phone")
    private String studentPhone;

    @ApiModelProperty("兴趣")
    @TableField("student_interest")
    private String studentInterest;

    @ApiModelProperty("梦想")
    @TableField("student_dream")
    private String studentDream;

    @ApiModelProperty("家庭情况")
    @TableField("family_status")
    private String familyStatus;

    @ApiModelProperty("监护人姓名")
    @TableField("guardian_name")
    private String guardianName;

    @ApiModelProperty("学校名称")
    @TableField("guardian_relation")
    private String guardianRelation;

    @ApiModelProperty("监护人电话")
    @TableField("guardian_phone")
    private String guardianPhone;

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
