package com.scaffolding.sophia.admin.biz.service.role;

import com.scaffolding.sophia.admin.api.entity.role.Role;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.role
 * @ClassName: RoleService
 * @Description:
 * @Version: 1.0
 */
public interface RoleService {


    /**
     * 根据用户id查询用户的角色
     *
     * @param userId 用户id
     * @return Role
     */
    Role getRoleByUserId(Long userId);
}
