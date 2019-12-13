package com.scaffolding.sophia.admin.biz.controller;

import com.scaffolding.sophia.admin.api.entity.dto.UserDto;
import com.scaffolding.sophia.admin.api.entity.bo.Permission;
import com.scaffolding.sophia.admin.api.entity.bo.Role;
import com.scaffolding.sophia.admin.api.entity.bo.User;
import com.scaffolding.sophia.admin.api.entity.vo.UserVo;
import com.scaffolding.sophia.admin.biz.service.authority.PermissionService;
import com.scaffolding.sophia.admin.biz.service.role.RoleService;
import com.scaffolding.sophia.admin.biz.service.user.UserService;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.base.support.BaseController;
import com.scaffolding.sophia.common.security.model.LoginUser;
import com.scaffolding.sophia.common.security.util.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/principal")
    @ApiOperation(value = "获取用户信息")
    public ApiResponse getUserInfo() {
        UserDto userDto = new UserDto();
        LoginUser loginUser = UserUtils.getLoginUser();
        User user = userService.loadUserByUserId(loginUser.getId());
        UserVo userVo = new UserVo();
        Role role = roleService.getRoleByUserId(loginUser.getId());
        List<Permission> authList = permissionService.findAuthorityByUserId(loginUser.getId());
        List<String> authCodeList = new ArrayList<>();
        List<String> roleCodeList = new ArrayList<>();
        for (Permission authority : authList) {
            authCodeList.add(authority.getCode());
        }
        roleCodeList.add(role.getRoleCode());
        BeanUtils.copyProperties(user, userVo);
        userDto.setSysUser(userVo);
        userDto.setPermissions(authCodeList);
        userDto.setRoles(roleCodeList);
        userDto.setMenus(authList);

        // 更新用户登录信息
        User userInfo = userService.loadUserByUserId(user.getId());
        userInfo.setLastLoginIp(this.getIpAddr());
        userInfo.setLastLoginTime(LocalDateTime.now());
        userInfo.setUpdateTime(LocalDateTime.now());
        userInfo.setUpdateUser(UserUtils.getLoginUser().getId());
        userService.updateUserInfo(userInfo);
        return success(userDto);
    }

    @GetMapping("")
    @ApiOperation(value = "用户信息分页")
    public ApiResponse getUserList() {
        Map<String, Object> param = getParams();
        param.put("deptPid", UserUtils.getLoginUser().getDeptId());
        param.put("userId", UserUtils.getLoginUser().getId());
        return success(userService.queryUserList(param));
    }

    @PutMapping("/info")
    @ApiOperation(value = "添加用户")
    public ApiResponse saveUser(@RequestBody User user) {
        if (user == null) {
            return fail();
        }
        User checkUser = userService.loadUserByUserName(user.getUsername());
        if (checkUser != null) {
            return fail("用户名已存在，请重新输入");
        }
        return handle(userService.saveUser(user));
    }

    @PutMapping("/info/{id}")
    @ApiOperation(value = "修改用户")
    public ApiResponse updateUser(@RequestBody User user, @ApiParam("用户id") @PathVariable Long id) {
        if (user == null) {
            return fail();
        }
        user.setId(id);
        return handle(userService.updateUser(user));
    }


    @PostMapping("/status/{id}")
    @ApiOperation(value = "修改用户状态  0无效 1有效 ")
    public ApiResponse updateStatus(@ApiParam("用户id") @PathVariable Long id, Integer status) {
        return handle(userService.updateStatus(id, status));
    }


    @GetMapping("/info/{id}")
    @ApiOperation(value = "根据用户id获取用户信息")
    public ApiResponse getUserByUserId(@ApiParam("用户id") @PathVariable Long id) {
        return success(userService.loadUserByUserId(id));
    }

    @DeleteMapping("/info/{id}")
    @ApiOperation(value = "根据用户id删除用户信息")
    public ApiResponse deleteUser(@ApiParam("用户id") @PathVariable Long id) {
        return handle(userService.deleteUser(id));
    }

    @GetMapping("/api")
    @ApiOperation(value = "根据用户名(手机号或者邮箱)获取用户信息")
    public ApiResponse getUserByUserName(@ApiParam("用户名(手机号或者邮箱)") @RequestParam String username) {
        return success(userService.loadUserByUserName(username));
    }

}
