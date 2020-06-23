package com.scaffolding.sophia.common.base.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LHL
 */
@Data
public class PageDto implements Serializable {

    /**
     * 页码
     */
    private Integer pageIndex;

    /**
     * 页码
     */
    private Integer currentPage;

    /**
     * 分页
     */
    private Integer pageSize;
}
