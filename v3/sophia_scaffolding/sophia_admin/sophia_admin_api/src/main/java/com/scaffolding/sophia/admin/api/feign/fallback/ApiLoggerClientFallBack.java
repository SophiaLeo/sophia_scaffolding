package com.scaffolding.sophia.admin.api.feign.fallback;

import com.scaffolding.sophia.admin.api.entity.bo.ApiLogger;
import com.scaffolding.sophia.admin.api.feign.client.ApiLoggerClient;
import com.scaffolding.sophia.common.base.constants.ServiceNameConstants;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.feign.fallback
 * @ClassName: ApiLoggerClientFallBack
 * @Description:
 * @Version: 1.0
 */
@Component
public class ApiLoggerClientFallBack implements ApiLoggerClient {

    private final Logger logger = LoggerFactory.getLogger(ApiLoggerClientFallBack.class);

    @Override
    public ApiResponse saveLog(ApiLogger apiLogger) {
        logger.error("调用sophia-admin服务saveLog方法失败!");
        return ApiResponse.hystrixError(ServiceNameConstants.SOPHIA_ADMIN, "saveLog");
    }
}
