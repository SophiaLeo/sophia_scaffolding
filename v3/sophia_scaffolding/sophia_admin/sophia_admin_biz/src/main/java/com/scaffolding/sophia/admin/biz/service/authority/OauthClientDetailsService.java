package com.scaffolding.sophia.admin.biz.service.authority;

import com.scaffolding.sophia.admin.api.entity.authority.OauthClientDetails;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.authority
 * @ClassName: oauthClientDetailsService
 * @Description:
 * @Version: 1.0
 */
public interface OauthClientDetailsService{

    /**
     * 根据clientId查询OauthClientDetails
     *
     * @param clientId clientId
     * @return OauthClientDetails
     */
    OauthClientDetails findOauthClientDetailsByClientId(String clientId);

}
