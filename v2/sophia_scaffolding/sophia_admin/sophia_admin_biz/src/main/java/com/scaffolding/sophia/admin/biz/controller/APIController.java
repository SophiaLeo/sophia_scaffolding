package com.scaffolding.sophia.admin.biz.controller;

import com.scaffolding.sophia.admin.api.dto.UserDto;
import com.scaffolding.sophia.admin.api.entity.authority.Authority;
import com.scaffolding.sophia.admin.api.entity.role.Role;
import com.scaffolding.sophia.admin.api.entity.user.User;
import com.scaffolding.sophia.admin.api.vo.UserVo;
import com.scaffolding.sophia.admin.biz.service.authority.AuthorityService;
import com.scaffolding.sophia.admin.biz.service.role.RoleService;
import com.scaffolding.sophia.admin.biz.service.user.UserService;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.base.support.BaseController;
import com.scaffolding.sophia.common.security.model.LoginUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
public class APIController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private RoleService roleService;


    @GetMapping("/principal")
    public ApiResponse getUserInfo() {
        UserDto userDto = new UserDto();
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.loadUserByUserId(loginUser.getId());
        UserVo userVo = new UserVo();
        Role role = roleService.getRoleByUserId(loginUser.getId());
        List<Authority> authList = authorityService.findAuthorityByUserId(loginUser.getId());
        List<String> authCodeList = new ArrayList<>();
        List<String> roleCodeList = new ArrayList<>();
        List<String> menuCodeList = new ArrayList<>();
        for (Authority authority : authList) {
            authCodeList.add(authority.getAuthCode());
            menuCodeList.add(authority.getAuthCode());
        }
        roleCodeList.add(role.getRoleCode());
        BeanUtils.copyProperties(user, userVo);
        userDto.setSysUser(userVo);
        userDto.setPermissions(authCodeList);
        userDto.setRoles(roleCodeList);
        userDto.setMenus(menuCodeList);
        return success(userDto);
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ApiOperation(value = "登录接口")
    public ApiResponse webLogin(@RequestParam String userName, @RequestParam String password){
        UserVo result = userService.loginByPassword(userName, password);
        if(null != result){
            return success(result);
        }
        return fail("登陆失败");
    }


}
