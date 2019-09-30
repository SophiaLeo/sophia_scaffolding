package com.scaffolding.sophia.admin.api.feign.fallback;

import com.scaffolding.sophia.admin.api.feign.client.AuthorityClient;
import com.scaffolding.sophia.common.base.constants.ServiceNameConstants;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.feign.fallback
 * @ClassName: AuthorityClientFallBack
 * @Description:
 * @Version: 1.0
 */
@Component
public class AuthorityClientFallBack implements AuthorityClient {

    private final Logger logger = LoggerFactory.getLogger(AuthorityClientFallBack.class);

    @Override
    public ApiResponse getAuthorityByUserId(Long id) {
        logger.error("调用sophia-admin服务getAuthorityByUserId方法失败!");
        return ApiResponse.hystrixError(ServiceNameConstants.SOPHIA_ADMIN, "getAuthorityByUserId");
    }
}
