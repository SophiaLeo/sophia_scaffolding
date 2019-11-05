package com.scaffolding.sophia.admin.biz.service.authority;

import com.scaffolding.sophia.admin.api.entity.authority.Authority;

import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.authority
 * @ClassName: AuthorityService
 * @Description:
 * @Version: 1.0
 */
public interface AuthorityService {

    /**
     * 根据用户id查询用户的权限
     *
     * @param id 用户id
     * @return List<Authority>
     */
    List<Authority> findAuthorityByUserId(Long id);
}
