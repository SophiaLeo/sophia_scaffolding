package com.scaffolding.sophia.admin.api.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LHL
 */
@Data
public class PermissionDto implements Serializable {

    /**
     * 用户id
     */
    private String userId;

    /**
     * id
     * */
    private String id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 路径
     */
    private String urlPath;

    /**
     * 父级权限
     */
    private String pid;

    /**
     * 菜单类型 （0菜单 1按钮）
     */
    private Integer type;

    /**
     * 图标
     */
    private String iconPath;

    /**
     * 排序
     */
    private Integer sort;
}
