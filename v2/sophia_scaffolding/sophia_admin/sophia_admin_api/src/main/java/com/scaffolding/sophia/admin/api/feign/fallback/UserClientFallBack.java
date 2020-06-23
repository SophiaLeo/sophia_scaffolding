package com.scaffolding.sophia.admin.api.feign.fallback;

import com.scaffolding.sophia.admin.api.feign.client.UserClient;
import com.scaffolding.sophia.common.base.constants.ServiceNameConstants;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserClientFallBack implements UserClient {

    @Override
    public ApiResponse getUserByUserName(String username) {
        log.error("调用sophia-admin服务getUserByUserName方法失败,参数username:{}",username);
        return ApiResponse.hystrixError(ServiceNameConstants.SOPHIA_ADMIN, "getUserByUserName");
    }

    @Override
    public ApiResponse loadUserByUserId(String id) {
        log.error("调用sophia-admin服务getUserByUserId方法失败,参数id:{}",id);
        return ApiResponse.hystrixError(ServiceNameConstants.SOPHIA_ADMIN, "getUserByUserId");
    }
}
