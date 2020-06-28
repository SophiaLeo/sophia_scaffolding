package com.scaffolding.sophia.admin.api.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author LHL
 */
@Data
@ApiModel(value = "UserDto",description = "用户")
public class UserDto implements Serializable {


    /**
     * 当前登录用户id
     */
    @ApiModelProperty("当前登录用户id")
    private String userId;

    /**
     * 角色id
     */
    @ApiModelProperty("角色id")
    private String roleId;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickname;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer age;

    /**
     * 性别 1:男 2:女
     */
    @ApiModelProperty("性别 1:男 2:女")
    private Integer sex;

    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("生日")
    private LocalDateTime birthday;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 省
     */
    @ApiModelProperty("省")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty("市")
    private String city;

    /**
     * 区
     */
    @ApiModelProperty("区")
    private String area;

    /**
     * 住址
     */
    @ApiModelProperty("住址")
    private String address;

    /**
     * 后台用户头像
     */
    @ApiModelProperty("后台用户头像")
    private String headImage;

    /**
     * 部门id 一个用户只有 一个部门
     */
    @ApiModelProperty("部门id")
    private String deptId;

    /**
     * 公司id
     */
    @ApiModelProperty("公司id")
    private String compId;

}
