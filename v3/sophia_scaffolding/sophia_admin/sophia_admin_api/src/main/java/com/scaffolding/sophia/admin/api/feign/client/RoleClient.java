package com.scaffolding.sophia.admin.api.feign.client;

import com.scaffolding.sophia.admin.api.feign.fallback.RoleClientFallBack;
import com.scaffolding.sophia.common.base.constants.ServiceNameConstants;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.feign.config.FeignRequestInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.feign.client
 * @ClassName: RoleClient
 * @Description:
 * @Version: 1.0
 */
@FeignClient(contextId = "roleClient", name = ServiceNameConstants.SOPHIA_ADMIN, configuration = FeignRequestInterceptorConfig.class, fallback = RoleClientFallBack.class)
public interface RoleClient {

    @GetMapping("/role/web/info/user/{id}")
    ApiResponse getRoleByUserId(@PathVariable String id);
}
