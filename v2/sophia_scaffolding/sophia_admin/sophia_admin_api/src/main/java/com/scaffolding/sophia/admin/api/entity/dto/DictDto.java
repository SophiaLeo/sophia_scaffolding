package com.scaffolding.sophia.admin.api.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author LHL
 */
@Data
public class DictDto implements Serializable {

    private String userId;


    private String id;


    private String pid;

    /**
     * 字典值
     * */
    private Integer value;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 子类
     */
    private List<DictDto> children;

}
