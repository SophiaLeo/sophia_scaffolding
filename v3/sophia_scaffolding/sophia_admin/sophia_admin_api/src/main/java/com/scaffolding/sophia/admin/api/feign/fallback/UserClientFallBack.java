package com.scaffolding.sophia.admin.api.feign.fallback;

import com.scaffolding.sophia.admin.api.feign.client.UserClient;
import com.scaffolding.sophia.common.base.constants.ServiceNameConstants;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.feign.fallback
 * @ClassName: UserClientFallBack
 * @Description:
 * @Version: 1.0
 */
@Component
public class UserClientFallBack implements UserClient {

    private final Logger logger = LoggerFactory.getLogger(UserClientFallBack.class);

    @Override
    public ApiResponse getUserByUserName(String username) {
        logger.error("调用sophia-admin服务getUserByUserName方法失败!");
        return ApiResponse.hystrixError(ServiceNameConstants.SOPHIA_ADMIN, "getUserByUserName");
    }

    @Override
    public ApiResponse getUserByUserId(Long id) {
        logger.error("调用sophia-admin服务getUserByUserId方法失败!");
        return ApiResponse.hystrixError(ServiceNameConstants.SOPHIA_ADMIN, "getUserByUserId");
    }
}
