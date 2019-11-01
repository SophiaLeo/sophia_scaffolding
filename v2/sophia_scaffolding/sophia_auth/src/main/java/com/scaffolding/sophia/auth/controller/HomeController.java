package com.scaffolding.sophia.auth.controller;

import com.scaffolding.sophia.admin.api.feign.client.ApiClient;
import com.scaffolding.sophia.admin.api.feign.client.UserClient;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.base.support.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
    private TokenStore tokenStore;



    @GetMapping("/principal")
    public Principal user(Principal member) {
        //获取当前用户信息
        return member;
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
    @DeleteMapping("/logout")
    public ApiResponse logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
        if (StringUtils.isBlank(authHeader)) {
            return fail("退出失败，token 为空");
        }
        //注销当前用户
        String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, StringUtils.EMPTY).trim();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        tokenStore.removeAccessToken(accessToken);
        OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
        tokenStore.removeRefreshToken(refreshToken);
        return success("注销成功");
    }

}
