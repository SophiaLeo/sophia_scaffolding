package com.scaffolding.sophia.admin.api.feign.client;

import com.scaffolding.sophia.admin.api.entity.bo.ApiLogger;
import com.scaffolding.sophia.admin.api.feign.fallback.ApiLoggerClientFallBack;
import com.scaffolding.sophia.common.base.constants.ServiceNameConstants;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.feign.config.FeignRequestInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.feign.client
 * @ClassName: ApiLoggerClient
 * @Description:
 * @Version: 1.0
 */
@FeignClient(contextId = "apiLoggerClient", name = ServiceNameConstants.SOPHIA_ADMIN, configuration = FeignRequestInterceptorConfig.class, fallback = ApiLoggerClientFallBack.class)
public interface ApiLoggerClient {

    /**
     * 保存日志
     * @param apiLogger 日志实体
     * @return ApiResponse
     */
    @PostMapping("/log/api/add")
    ApiResponse saveLog(@RequestBody ApiLogger apiLogger);

}
