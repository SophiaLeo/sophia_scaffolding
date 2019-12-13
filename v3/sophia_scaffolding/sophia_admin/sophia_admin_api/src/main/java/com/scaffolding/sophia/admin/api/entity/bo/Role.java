package com.scaffolding.sophia.admin.api.entity.bo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @Package: com.scaffolding.sophia.admin.api.entity.role.entity
 * @ClassName: Role
 * @Description:
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName("sys_role")
@ApiModel(value = "Role",description = "角色设置")
public class Role extends BaseBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     * */
    @TableId(type = IdType.AUTO)
    private Long id;

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
     * 部门id
     */
    private Long deptId;

}
