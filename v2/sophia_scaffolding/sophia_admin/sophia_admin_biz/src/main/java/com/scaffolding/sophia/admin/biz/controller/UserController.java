package com.scaffolding.sophia.admin.biz.controller;

import com.scaffolding.sophia.admin.biz.service.user.UserService;
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
 * @ClassName: UserController
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/info/{id}")
    @ApiOperation(value = "根据用户id获取用户信息")
    public ApiResponse getUserByUserId(@PathVariable Long id) {
        return success(userService.loadUserByUserId(id));
    }

    @GetMapping("/api")
    @ApiOperation(value = "根据用户名获取用户信息")
    public ApiResponse getUserByUserName(@RequestParam String username) {
        return success(userService.loadUserByUserName(username));
    }
}
