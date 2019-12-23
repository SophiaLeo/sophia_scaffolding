package com.scaffolding.sophia.common.base.bo;

import lombok.Data;

import java.io.Serializable;
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
public class BaseBo implements Serializable {

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    private String updateUser;
}
