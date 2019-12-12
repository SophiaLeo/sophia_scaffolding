package com.scaffolding.sophia.admin.api.feign.fallback;

import com.scaffolding.sophia.admin.api.feign.client.ApiClient;
import com.scaffolding.sophia.common.base.constants.ServiceNameConstants;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.feign.fallback
 * @ClassName: ApiClientFallBack
 * @Description:
 * @Version: 1.0
 */
@Component
public class ApiClientFallBack implements ApiClient {

    private final Logger logger = LoggerFactory.getLogger(ApiClientFallBack.class);

    @Override
    public ApiResponse getUserInfo() {
        logger.error("调用sophia-admin服务getUserInfo方法失败!");
        return ApiResponse.hystrixError(ServiceNameConstants.SOPHIA_ADMIN , "getUserInfo");
    }

}
