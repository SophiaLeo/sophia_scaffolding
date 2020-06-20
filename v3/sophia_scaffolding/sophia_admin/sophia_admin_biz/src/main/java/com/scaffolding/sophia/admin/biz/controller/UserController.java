package com.scaffolding.sophia.admin.biz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.scaffolding.sophia.admin.api.entity.bo.Role;
import com.scaffolding.sophia.admin.api.entity.bo.User;
import com.scaffolding.sophia.admin.api.entity.dto.UserDto;
import com.scaffolding.sophia.admin.api.entity.dto.UserSearchDto;
import com.scaffolding.sophia.admin.api.entity.vo.LoginUserVo;
import com.scaffolding.sophia.admin.api.entity.vo.PermissionVo;
import com.scaffolding.sophia.admin.api.entity.vo.UserVo;
import com.scaffolding.sophia.admin.biz.service.PermissionService;
import com.scaffolding.sophia.admin.biz.service.RoleService;
import com.scaffolding.sophia.admin.biz.service.UserService;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.base.support.BaseController;
import com.scaffolding.sophia.common.log.annotation.SysLog;
import com.scaffolding.sophia.common.security.model.LoginUser;
import com.scaffolding.sophia.common.security.util.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        LoginUserVo loginUserVo = new LoginUserVo();
        LoginUser loginUser = UserUtils.getLoginUser();
        UserVo userVo = userService.loadUserByUserId(loginUser.getId());
        Role role = roleService.getRoleByUserId(loginUser.getId());
        List<PermissionVo> authList = permissionService.findAuthorityByUserId(loginUser.getId());
        List<String> authCodeList = new ArrayList<>();
        List<String> roleCodeList = new ArrayList<>();
        for (PermissionVo authority : authList) {
            authCodeList.add(authority.getCode());
        }
        roleCodeList.add(role.getRoleCode());
        loginUserVo.setSysUser(userVo);
        loginUserVo.setPermissions(authCodeList);
        loginUserVo.setRoles(roleCodeList);
        loginUserVo.setMenus(authList);
        // 更新用户登录信息
        User userInfo = new User();
        BeanUtils.copyProperties(userVo,userInfo);
        userInfo.setLastLoginIp(this.getIpAddr());
        userInfo.setLastLoginTime(LocalDateTime.now());
        userInfo.setUpdateTime(LocalDateTime.now());
        userInfo.setUpdateUser(UserUtils.getLoginUser().getId());
        userService.updateUserInfo(userInfo);
        return success(loginUserVo);
    }

    @GetMapping("/web")
    @ApiOperation(value = "用户信息分页")
    public ApiResponse getUserList(@ModelAttribute UserSearchDto userSearchDto) {
        // Map<String, Object> param = getParams();
        return success(userService.queryUserList(userSearchDto));
    }

    @GetMapping("/web/info/{id}")
    @ApiOperation(value = "根据用户id获取用户信息")
    public ApiResponse getUserByUserId(@ApiParam("用户id") @PathVariable String id) {
        return success(userService.loadUserByUserId(id));
    }

    @GetMapping(value = "/web/check")
    @ApiOperation(value = "校验用户某属性值是否重复-后端管理用户管理", notes = "校验用户某属性值是否重复-后端管理用户管理")
    public ApiResponse checkUserName(@ApiParam(value = "用户id", required = false) @RequestParam(required = false) String id
            , @ApiParam(value = "用户名称或手机号或用户昵称", required = true) @RequestParam String str
            , @ApiParam(value = "校验类型 1:用户名称 2:用户昵称 3:手机", required = true) @RequestParam Integer type) {
        Map<String, Object> map = null;
        boolean flag = false;
        switch (type) {
            case 1:
                map = userService.checkUserName(str, id);
                flag = (boolean) map.get("flag");
                break;
            case 2:
                map = userService.checkNickname(str, id);
                flag = (boolean) map.get("flag");
                break;
            case 3:
                map = userService.checkPhone(str, id);
                flag = (boolean) map.get("flag");
                break;
            default:
                break;
        }
        if (flag) {
            return success("成功");
        } else {
            String msg = (String) map.get("msg");
            return success(msg);
        }
    }

    @GetMapping("/web/judge/{id}")
    @ApiOperation(value = "判断用户原密码是否正确-后台管理用户管理", notes = "判断用户原密码是否正确-后台管理用户管理")
    public ApiResponse judgePassword(@RequestParam String password, @ApiParam("用户id") @PathVariable String id) {
        if (StringUtils.isBlank(id)) {
            return fail("id不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return fail("密码不能为空");
        }
        if (userService.judgePassword(id, password)) {
            return success("填写成功");
        } else {
            return fail("填写正确的密码");
        }
    }

    @SysLog("添加用户")
    @PutMapping("/web/add")
    @ApiOperation(value = "添加用户")
    public ApiResponse saveUser(@RequestBody UserDto userDto) {
        if (userDto == null) {
            return fail();
        }
        return handle(userService.saveUser(userDto));
    }

    @SysLog("修改用户")
    @PutMapping("/web/update")
    @ApiOperation(value = "修改用户")
    public ApiResponse updateUser(@RequestBody UserDto userDto) {
        if (userDto == null) {
            return fail();
        }
        return handle(userService.updateUser(userDto));
    }

    @SysLog("修改密码")
    @PutMapping("/web/updatePassword")
    @ApiOperation(value = "修改用户密码-后端管理用户管理", notes = "修改用户密码-后端管理用户管理")
    public ApiResponse updateWebPassword(@RequestBody UserDto userDto) {
        if (StringUtils.isBlank(userDto.getId())) {
            return fail("id不能为空");
        }
        if (StringUtils.isBlank(userDto.getPassword())) {
            return fail("新密码不能为空");
        }
        if (userService.updatePassword(userDto)) {
            return success("修改成功");
        } else {
            return fail("修改失败");
        }
    }


    @SysLog("分配用户角色")
    @PutMapping("/web/role")
    @ApiOperation(value = "分配用户角色-后端管理用户管理", notes = "分配用户角色-后端管理用户管理")
    public ApiResponse updateWebRole(@RequestBody UserDto userDto) {
        if (StringUtils.isBlank(userDto.getId())) {
            return fail("id不能为空");
        }
        if (StringUtils.isBlank(userDto.getRoleId())) {
            return fail("角色id不能为空");
        }
        if (userService.updateRole(userDto)) {
            return success("修改成功");
        } else {
            return fail("修改失败");
        }
    }

    @SysLog("修改用户状态")
    @PutMapping("/web/status/{id}")
    @ApiOperation(value = "修改用户状态  0无效 1有效 ")
    public ApiResponse updateStatus(@ApiParam("用户id") @PathVariable String id,@ApiParam("状态 0无效 1有效") Integer status) {
        return handle(userService.updateStatus(id, status));
    }


    @SysLog("删除用户")
    @DeleteMapping("/web/del/{id}")
    @ApiOperation(value = "根据用户id删除用户信息")
    public ApiResponse deleteUser(@ApiParam("用户id") @PathVariable String id) {
        return handle(userService.deleteUser(id));
    }

    @SysLog("批量删除用户")
    @DeleteMapping(value = "/web/delBatch")
    @ApiOperation(value = "批量删除用户管理-后端管理用户管理", notes = "批量删除用户管理-后端管理用户管理")
    public ApiResponse deleteBatchUser(@RequestBody String ids) {
        if (StringUtils.isBlank(ids)) {
            return fail("参数不能为空");
        }
        List<String> idList = JSON.parseArray(((JSONArray) JSON.parseObject(ids).get("ids")).toJSONString(), String.class);
        if (userService.deleteBatchUser(idList)) {
            return success("批量删除成功");
        } else {
            return fail("批量删除失败");
        }
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "根据用户id获取用户信息")
    public ApiResponse loadUserByUserId(@ApiParam("用户id") @PathVariable String id) {
        return success(userService.loadUserByUserId(id));
    }

    @GetMapping("/api/{username}")
    @ApiOperation(value = "根据用户名(手机号或者邮箱)获取用户信息")
    public ApiResponse getUserByUserName(@PathVariable String username) {
        return success(userService.loadUserByUserName(username));
    }

}
