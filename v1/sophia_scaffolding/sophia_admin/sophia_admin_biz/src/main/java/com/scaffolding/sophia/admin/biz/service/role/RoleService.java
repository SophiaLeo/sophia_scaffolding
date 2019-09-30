package com.scaffolding.sophia.admin.biz.service.role;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scaffolding.sophia.admin.biz.dao.role.RoleDao;
import com.scaffolding.sophia.admin.api.entity.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.role
 * @ClassName: RoleService
 * @Description:
 * @Version: 1.0
 */
@Service
public class RoleService extends ServiceImpl<RoleDao, Role> {

    @Autowired
    private  RoleDao roleDao;

    public Role getRoleByUserId(Long id) {
        return roleDao.getRoleByUserId(id);
    }
}
