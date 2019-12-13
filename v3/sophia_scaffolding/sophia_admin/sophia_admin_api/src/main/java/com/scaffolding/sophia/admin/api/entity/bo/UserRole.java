package com.scaffolding.sophia.admin.api.entity.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.entity.user.entity
 * @ClassName: UserRole
 * @Description:
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName("sys_user_role")
@ApiModel(value = "UserRole",description = "用户角色设置")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     * */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 用户id
     */
    private Long userId;
}
