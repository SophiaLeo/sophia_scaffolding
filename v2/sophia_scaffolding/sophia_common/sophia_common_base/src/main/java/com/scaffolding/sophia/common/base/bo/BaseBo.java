package com.scaffolding.sophia.common.base.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.base.bo
 * @ClassName: BaseBo
 * @Date: 2019/11/5 10:27
 * @Description:
 * @Version: 1.0
 */
@Data
public class BaseBo {

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    private Long updateUser;
}
