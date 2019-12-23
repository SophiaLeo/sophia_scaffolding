package com.scaffolding.sophia.admin.api.entity.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * @author LHL
 */
@Data
public class RoleDto implements Serializable {


    /**
     * 用户id
     */
    private String userId;

    /**
     * id
     * */
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编号
     */
    private String roleCode;

    /**
     * 描述
     */
    private String description;

    /**
     * 角色类型 1:后台角色 0:前台角色
     */
    private Integer roleType;
}
