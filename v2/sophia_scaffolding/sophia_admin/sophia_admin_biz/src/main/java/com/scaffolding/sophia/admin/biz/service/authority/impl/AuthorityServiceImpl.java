package com.scaffolding.sophia.admin.biz.service.authority.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scaffolding.sophia.admin.api.entity.authority.Authority;
import com.scaffolding.sophia.admin.biz.dao.authority.AuthorityDao;
import com.scaffolding.sophia.admin.biz.service.authority.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service("authorityService")
public class AuthorityServiceImpl extends ServiceImpl<AuthorityDao, Authority> implements AuthorityService {

    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public List<Authority> findAuthorityByUserId(Long id) {
        return authorityDao.findAuthorityByUserId(id);
    }
}
