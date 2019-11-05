package com.scaffolding.sophia.admin.biz.dao.authority;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scaffolding.sophia.admin.api.entity.authority.OauthClientDetails;
import org.springframework.stereotype.Repository;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.dao.authority
 * @ClassName: OauthClientDetailsDao
 * @Description:
 * @Version: 1.0
 */
@Repository
public interface OauthClientDetailsDao extends BaseMapper<OauthClientDetails> {

    /**
     * 根据clientId查询OauthClientDetails
     *
     * @param clientId clientId
     * @return OauthClientDetails
     */
    OauthClientDetails getOauthClientDetailsByClientId(String clientId);

    /**
     * 根据clientId查询resourceIds
     *
     * @param clientId clientId
     * @return String
     */
    String getResourceIdsByClientId(String clientId);

}
