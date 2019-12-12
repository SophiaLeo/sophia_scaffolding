package com.scaffolding.sophia.admin.api.dto;

import com.scaffolding.sophia.admin.api.entity.authority.Permission;
import com.scaffolding.sophia.admin.api.vo.UserVo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.dto
 * @ClassName: UserDto
 * @Description:
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户
     * */
    private UserVo sysUser;
    /**
     * 权限
     * */
    private List<String> permissions;
    /**
     * 角色
     * */
    private List<String> roles;
    /**
     * 菜单
     * */
    private List<Permission> menus;
}
