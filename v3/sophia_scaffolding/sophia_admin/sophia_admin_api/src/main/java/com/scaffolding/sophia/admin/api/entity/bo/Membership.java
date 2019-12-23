package com.scaffolding.sophia.admin.api.entity.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.scaffolding.sophia.common.base.bo.BaseBo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.entity.bo
 * @ClassName: Membership
 * @Description:
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName("sys_membership")
@ApiModel(value = "Membership",description = "前端会员")
public class Membership extends BaseBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * appid
     */
    private String appId;

    /**
     * 用户头像
     */
    private String imgPath;

    /**
     * 是否删除 (1 是  0否)
     */
    private Integer isDeleted;

    /**
     * 0无效 1有效
     */
    private Integer status;
}
