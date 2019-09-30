package com.scaffolding.sophia.auth.controller;

import com.scaffolding.sophia.admin.api.feign.client.ApiClient;
import com.scaffolding.sophia.admin.api.feign.client.UserClient;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.base.support.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.auth.controller
 * @ClassName: ApiController
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/home")
public class HomeController extends BaseController {

    @Autowired
    private UserClient userClient;
    @Autowired
    private ApiClient apiClient;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/principal")
    public ApiResponse getUserInfo(Authentication auth) {
        return success(auth);
    }

    @GetMapping("/test")
    public ApiResponse getUserInfo() {
        return apiClient.getUserInfo();
    }


    @GetMapping("/test/{userId}")
    public ApiResponse getUserByUserId(@PathVariable Long userId) {
        return userClient.getUserByUserId(userId);
    }

    /**
     * 清除token（注销登录）
     */
    @GetMapping("/logout")
    public ApiResponse logout(@RequestParam String token) {
        return handle(consumerTokenServices.revokeToken(token));
    }

}
