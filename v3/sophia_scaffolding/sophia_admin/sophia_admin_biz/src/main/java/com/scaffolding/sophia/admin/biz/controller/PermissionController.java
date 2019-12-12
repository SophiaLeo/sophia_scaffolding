package com.scaffolding.sophia.admin.biz.controller;

import com.scaffolding.sophia.admin.biz.service.authority.PermissionService;
import com.scaffolding.sophia.admin.biz.service.authority.OauthClientDetailsService;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.base.support.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.controller
 * @ClassName: AuthorityController
 * @Date: 2019/9/28 13:59
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/authority")
@Api(tags = "权限管理")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    @GetMapping("/api/{id}")
    @ApiOperation(value = "根据用户id获取用户权限信息")
    public ApiResponse getAuthorityByUserId(@PathVariable Long id) {
        return success(permissionService.findAuthorityByUserId(id));
    }

    @GetMapping("/api/info")
    @ApiOperation(value = "根据clientId获取认证客户端详情信息")
    public ApiResponse getOauthClientDetailsByClientId(@RequestParam String clientId) {
        return success(oauthClientDetailsService.findOauthClientDetailsByClientId(clientId));
    }



}
