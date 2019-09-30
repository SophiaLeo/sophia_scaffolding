package com.scaffolding.sophia.admin.biz.service.authority;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scaffolding.sophia.admin.api.entity.authority.OauthClientDetails;
import com.scaffolding.sophia.admin.biz.dao.authority.OauthClientDetailsDao;
import com.scaffolding.sophia.common.base.constants.GlobalsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.authority
 * @ClassName: oauthClientDetailsService
 * @Description:
 * @Version: 1.0
 */
@Service
public class OauthClientDetailsService extends ServiceImpl<OauthClientDetailsDao, OauthClientDetails> {

    @Autowired
    private OauthClientDetailsDao oauthClientDetailsDao;

    @Cacheable(value= GlobalsConstants.REDIS_CLIENT_CACHE,unless = "#result == null", key="T(com.scaffolding.sophia.common.base.constants.GlobalsConstants).CLIENT_DETAILS_KEY.concat(T(String).valueOf(#clientId))")
    public OauthClientDetails findOauthClientDetailsByClientId(String clientId) {
        return oauthClientDetailsDao.getOauthClientDetailsByClientId(clientId);
    }
}
