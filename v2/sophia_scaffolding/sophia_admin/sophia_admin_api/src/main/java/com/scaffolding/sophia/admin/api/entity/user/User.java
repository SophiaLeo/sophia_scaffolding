package com.scaffolding.sophia.admin.api.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.entity.user.entity
 * @ClassName: User
 * @Description:
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 最后登陆时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 是否删除
     */
    private Integer isDeleted;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 头像
     */
    private String headImage;

    /**
     * 部门id 一个用户只有 一个部门
     */
    private Long deptId;

    /**
     * 公司id
     */
    private Long compId;

}
