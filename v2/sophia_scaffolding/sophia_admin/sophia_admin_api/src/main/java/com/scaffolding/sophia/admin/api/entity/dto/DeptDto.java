package com.scaffolding.sophia.admin.api.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.entity.dto
 * @ClassName: DeptDto
 * @Date: 2019/12/20 13:39
 * @Description:
 * @Version: 1.0
 */
@Data
public class DeptDto implements Serializable {

    /**
     * 用户id
     */
    private String userId;

    /**
     * id
     * */
    private String id;

    /**
     * 排序
     */
    private Integer num;

    /**
     * 父部门id
     */
    private String pid;

    /**
     * 简称
     */
    private String simpleName;

    /**
     * 全称
     */
    private String fullName;

    /**
     * 提示
     */
    private String tips;

    /**
     * 地址
     */
    private String address;

    /**
     * 部门所在的公司id
     */
    private String compId;
}
