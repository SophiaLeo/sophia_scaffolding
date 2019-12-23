package com.scaffolding.sophia.admin.api.feign.client;

import com.scaffolding.sophia.admin.api.feign.fallback.AuthorityClientFallBack;
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
 * @ClassName: AuthorityClient
 * @Description:
 * @Version: 1.0
 */
@FeignClient(contextId = "authorityClient", name = ServiceNameConstants.SOPHIA_ADMIN, configuration = FeignRequestInterceptorConfig.class, fallback = AuthorityClientFallBack.class)
public interface AuthorityClient {

    @GetMapping("/authority/api/{id}")
    ApiResponse getAuthorityByUserId(@PathVariable String id);

    @GetMapping("/authority/api/info")
    ApiResponse getOauthClientDetailsByClientId(@RequestParam String clientId);
}
