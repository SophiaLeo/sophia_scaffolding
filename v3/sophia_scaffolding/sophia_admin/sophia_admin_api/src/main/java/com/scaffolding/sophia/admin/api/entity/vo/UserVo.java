package com.scaffolding.sophia.admin.api.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.entity.vo
 * @ClassName: UserVo
 * @Description:
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     * */
    @ApiModelProperty(value = "主键id")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @JsonIgnore
    private String password;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    private LocalDateTime birthday;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 省
     */
    @ApiModelProperty(value = "省")
    private String province;
    /**
     * 市
     */
    @ApiModelProperty(value = "市")
    private String city;
    /**
     * 区
     */
    @ApiModelProperty(value = "区")
    private String area;
    /**
     * 住址
     */
    @ApiModelProperty(value = "住址")
    private String address;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态 0无效 1有效")
    private Integer status;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String headImage;

    /**
     * 部门id 一个用户只有 一个部门
     */
    @ApiModelProperty(value = "部门id")
    private Long deptId;

    /**
     * 公司id
     */
    @ApiModelProperty(value = "公司id")
    private Long compId;

    @ApiModelProperty(value = "token")
    private String accessToken;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "部门名称")
    private String deptName;
}
