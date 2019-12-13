package com.scaffolding.sophia.admin.biz.service.authority;

import com.scaffolding.sophia.admin.api.entity.bo.Permission;

import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.authority
 * @ClassName: AuthorityService
 * @Description:
 * @Version: 1.0
 */
public interface PermissionService {

    /**
     * 根据用户id查询用户的权限
     *
     * @param id 用户id
     * @return List<Permission>
     */
    List<Permission> findAuthorityByUserId(Long id);

    /**
     * 根据角色id查询权限
     *
     * @param id 角色id
     * @return List<Permission>
     */
    List<Permission> findAuthorityByRoleId(Long id);
}
