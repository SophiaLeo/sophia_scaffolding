package com.scaffolding.sophia.admin.biz.service.authority;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scaffolding.sophia.admin.biz.dao.authority.AuthorityDao;
import com.scaffolding.sophia.admin.api.entity.authority.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.authority
 * @ClassName: AuthorityService
 * @Description:
 * @Version: 1.0
 */
@Service
public class AuthorityService extends ServiceImpl<AuthorityDao, Authority> {

    @Autowired
    private AuthorityDao authorityDao;

    public List<Authority> findAuthorityByUserId(Long id) {
        return authorityDao.findAuthorityByUserId(id);
    }
}
