package com.scaffolding.sophia.admin.biz.service.authority.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scaffolding.sophia.admin.api.entity.bo.Permission;
import com.scaffolding.sophia.admin.biz.dao.authority.PermissionMapper;
import com.scaffolding.sophia.admin.biz.service.authority.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.authority.impl
 * @ClassName: AuthorityServiceImpl
 * @Date: 2019/11/5 09:30
 * @Description:
 * @Version: 1.0
 */
@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<Permission> findAuthorityByUserId(Long id) {
        return baseMapper.findAuthorityByUserId(id);
    }

    @Override
    public List<Permission> findAuthorityByRoleId(Long id) {
        return baseMapper.findAuthorityByRoleId(id);
    }
}
