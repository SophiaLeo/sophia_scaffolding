package com.scaffolding.sophia.admin.biz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.scaffolding.sophia.admin.api.entity.dto.RoleDto;
import com.scaffolding.sophia.admin.api.entity.dto.RoleSearchDto;
import com.scaffolding.sophia.admin.api.entity.vo.RoleVo;
import com.scaffolding.sophia.admin.biz.service.PermissionService;
import com.scaffolding.sophia.admin.biz.service.RoleService;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.base.support.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.controller
 * @ClassName: RoleController
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色管理")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/web/info/user/{id}")
    @ApiOperation(value = "根据用户id获取用户角色信息")
    public ApiResponse getRoleByUserId(@PathVariable String id) {
        return success(roleService.getRoleByUserId(id));
    }

    @GetMapping("/web/list")
    @ApiOperation(value = "查询角色管理列表分页-后端管理角色管理", notes = "查询角色管理列表分页-后端管理角色管理")
    public ApiResponse getRolePage(@ModelAttribute RoleSearchDto params){
        // Map<String, Object> params = getParams();
        return success(roleService.queryRoleList(params));
    }

    @GetMapping("/web/select/list")
    @ApiOperation(value = "查询角色下拉框-后端管理角色管理", notes = "查询角色管理-后端管理角色管理")
    public ApiResponse getRoleList(@RequestParam(required = false) String userId){
        return success(roleService.queryRole(userId));
    }

    @GetMapping(value = "/web/info/{id}")
    @ApiOperation(value = "获取角色-后端管理角色管理", notes = "获取角色-后端管理角色管理")
    public ApiResponse getRole(@ApiParam(value = "角色id",required = true)@PathVariable String id){
        if(StringUtils.isBlank(id)){
            return fail("id不能为空");
        }
        RoleVo role = roleService.queryRoleById(id);
        if (null == role){
            return fail("未查找到该角色");
        }else{
            return success(role);
        }
    }

    @PostMapping(value = "/web/save")
    @ApiOperation(value = "新增角色管理-后端管理角色管理", notes = "新增角色管理-后端管理角色管理")
    public ApiResponse saveRole(@RequestBody RoleDto roleDto){
        Map<String, Object> map = roleService.saveOrUpdateRole(roleDto);
        boolean flag = (boolean) map.get("flag");
        String msg = (String) map.get("msg");
        if (flag){
            return success(msg);
        }else {
            return fail(msg);
        }
    }

    @PutMapping(value = "/web/update")
    @ApiOperation(value = "修改角色管理-后端管理角色管理", notes = "修改角色管理-后端管理角色管理")
    public ApiResponse updateRole(@RequestBody RoleDto roleDto){
        Map<String, Object> map = roleService.saveOrUpdateRole(roleDto);
        boolean flag = (boolean) map.get("flag");
        String msg = (String) map.get("msg");
        if (flag){
            return success(msg);
        }else {
            return fail(msg);
        }
    }

    @DeleteMapping(value = "/web/del/{id}")
    @ApiOperation(value = "删除角色管理-后端管理角色管理", notes = "删除角色管理-后端管理角色管理")
    public ApiResponse deleteRole(@ApiParam(value = "角色id",required = true)@PathVariable String id){
        if(StringUtils.isBlank(id)){
            return fail("id不能为空");
        }
        Map<String, Object> map = roleService.deleteRole(id);
        boolean flag = (boolean) map.get("flag");
        String msg = (String) map.get("msg");
        if (flag){
            return success(msg);
        }else {
            return fail(msg);
        }
    }


    @DeleteMapping(value = "/web/delBatch")
    @ApiOperation(value = "批量删除角色管理-后端管理角色管理", notes = "批量删除角色管理-后端管理角色管理")
    public ApiResponse deleteBatchRole(@RequestBody String ids){
        if (StringUtils.isBlank(ids)) {
            return fail("参数不能为空");
        }
        List<String> idList = JSON.parseArray(((JSONArray) JSON.parseObject(ids).get("ids")).toJSONString(), String.class);
        Map<String, Object> map = roleService.deleteBatchRole(idList);
        boolean flag = (boolean) map.get("flag");
        String msg = (String) map.get("msg");
        if (flag){
            return success(msg);
        }else {
            return fail(msg);
        }
    }

    @GetMapping("/web/permission/{id}")
    @ApiOperation("获取该角色拥有的权限-后端管理角色管理")
    public ApiResponse getPermissionByRoleId(@ApiParam("角色id") @PathVariable String id){
        if (StringUtils.isBlank(id)) {
            return fail("id不能为空");
        }
        return success(permissionService.getPermissionByRoleId(id));
    }


    @PostMapping("/web/permission/save/{id}")
    @ApiOperation("分配该角色选择的权限-后端管理角色管理")
    public ApiResponse savePermissionAndRole(@ApiParam("角色id") @PathVariable String id, @RequestBody String ids){
        if (StringUtils.isBlank(id)) {
            return fail("角色id不能为空");
        }
        if (StringUtils.isBlank(ids)) {
            return fail("参数不能为空");
        }
        List<String> idList = JSON.parseArray(((JSONArray) JSON.parseObject(ids).get("ids")).toJSONString(), String.class);
        if (permissionService.savePermissionAndRole(id,idList)){
            return success("分配成功");
        }else {
            return fail("分配失败");
        }
    }



}
