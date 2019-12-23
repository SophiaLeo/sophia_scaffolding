package com.scaffolding.sophia.admin.api.feign.client;

import com.scaffolding.sophia.admin.api.feign.fallback.ApiClientFallBack;
import com.scaffolding.sophia.common.base.constants.ServiceNameConstants;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.feign.config.FeignRequestInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.feign.client
 * @ClassName: ApiClient
 * @Description:
 * @Version: 1.0
 */
@FeignClient(contextId = "apiClient", name = ServiceNameConstants.SOPHIA_ADMIN, configuration = FeignRequestInterceptorConfig.class, fallback = ApiClientFallBack.class)
public interface ApiClient {

    @GetMapping("/api/principal")
    ApiResponse getUserInfo();

}
