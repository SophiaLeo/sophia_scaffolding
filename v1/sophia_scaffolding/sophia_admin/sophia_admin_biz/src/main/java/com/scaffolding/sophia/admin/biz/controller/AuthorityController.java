package com.scaffolding.sophia.admin.biz.controller;

import com.scaffolding.sophia.admin.biz.service.authority.AuthorityService;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.base.support.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class AuthorityController extends BaseController {

    @Autowired
    private AuthorityService authorityService;

    @GetMapping("/api/{id}")
    public ApiResponse getAuthorityByUserId(@PathVariable Long id) {
        return success(authorityService.findAuthorityByUserId(id));
    }



}
