package com.scaffolding.sophia.admin.biz.controller;

import com.scaffolding.sophia.admin.api.vo.UserVo;
import com.scaffolding.sophia.admin.biz.service.user.UserService;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.base.support.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.controller
 * @ClassName: APIController
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api")
@Api(tags = "对外暴露的接口")
public class APIController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/token")
    @ApiOperation(value = "获取token接口")
    public ApiResponse getToken(@RequestParam String username, @RequestParam String password,@RequestParam String code, @RequestParam String randomStr){
        String result = userService.getToken(username, password,code,randomStr);
        if(StringUtils.isNotBlank(result)){
            return success("获取token成功",result);
        }
        return fail("获取token失败");
    }


    @PostMapping(value = "/login")
    @ApiOperation(value = "登录接口")
    public ApiResponse webLogin(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) String code, @RequestParam(required = false) String randomStr){
        UserVo result = userService.loginByPassword(username, password,code,randomStr);
        if(null != result){
            return success(result);
        }
        return fail("登陆失败");
    }

}
