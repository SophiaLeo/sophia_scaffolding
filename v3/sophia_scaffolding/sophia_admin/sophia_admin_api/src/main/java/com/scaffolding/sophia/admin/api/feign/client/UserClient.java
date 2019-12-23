package com.scaffolding.sophia.admin.api.feign.client;

import com.scaffolding.sophia.admin.api.feign.fallback.UserClientFallBack;
import com.scaffolding.sophia.common.base.constants.ServiceNameConstants;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.feign.config.FeignRequestInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.feign.client
 * @ClassName: UserClient
 * @Description:
 * @Version: 1.0
 */
@FeignClient(contextId = "userClient", name = ServiceNameConstants.SOPHIA_ADMIN, configuration = FeignRequestInterceptorConfig.class, fallback = UserClientFallBack.class)
public interface UserClient {

    @GetMapping("/user/api")
    ApiResponse getUserByUserName(@RequestParam String username);

    @GetMapping("/user/info/{id}")
    ApiResponse getUserByUserId(@PathVariable String id);
}
