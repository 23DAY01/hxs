package com.lypc.hxs.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Getter
@Setter
@TableName("admin")
@ApiModel(value = "Admin对象", description = "管理员表")
public class Admin {

    @ApiModelProperty("主键id")
    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;

    @ApiModelProperty("登录账号")
    @TableField("admin_name")
    private String adminName;

    @ApiModelProperty("登录密码")
    @TableField("admin_passwd")
    private String adminPasswd;


}
