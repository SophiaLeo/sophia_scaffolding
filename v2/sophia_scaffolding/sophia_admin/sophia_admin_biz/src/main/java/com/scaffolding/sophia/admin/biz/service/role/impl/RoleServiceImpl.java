package com.scaffolding.sophia.admin.biz.service.role.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scaffolding.sophia.admin.api.entity.role.Role;
import com.scaffolding.sophia.admin.biz.dao.role.RoleDao;
import com.scaffolding.sophia.admin.biz.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.role.impl
 * @ClassName: RoleServiceImpl
 * @Date: 2019/11/5 09:28
 * @Description:
 * @Version: 1.0
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

    @Autowired
    private  RoleDao roleDao;

    @Override
    public Role getRoleByUserId(Long userId) {
        return roleDao.getRoleByUserId(userId);
    }
}
