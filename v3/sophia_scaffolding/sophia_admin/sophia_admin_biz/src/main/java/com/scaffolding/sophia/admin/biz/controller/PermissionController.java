package com.scaffolding.sophia.admin.biz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.scaffolding.sophia.admin.api.entity.dto.PermissionDto;
import com.scaffolding.sophia.admin.api.entity.vo.PermissionVo;
import com.scaffolding.sophia.admin.biz.service.OauthClientDetailsService;
import com.scaffolding.sophia.admin.biz.service.PermissionService;
import com.scaffolding.sophia.common.base.dto.PageDto;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.base.support.BaseController;
import com.scaffolding.sophia.common.log.annotation.SysLog;
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
    public ApiResponse getAuthorityByUserId(@PathVariable String id) {
        return success(permissionService.findAuthorityByUserId(id));
    }

    @GetMapping("/api/info")
    @ApiOperation(value = "根据clientId获取认证客户端详情信息")
    public ApiResponse getOauthClientDetailsByClientId(@RequestParam String clientId) {
        return success(oauthClientDetailsService.findOauthClientDetailsByClientId(clientId));
    }

    @GetMapping("/web/list")
    @ApiOperation(value = "查询权限管理列表分页-后端管理权限管理", notes = "查询权限管理列表分页-后端管理权限管理")
    public ApiResponse getPermissionPage(@ModelAttribute PageDto params){
        // Map<String, Object> params = getParams();
        return success(permissionService.queryPermissionList(params));
    }

    @GetMapping("/web/tree")
    @ApiOperation(value = "查询权限树形结构-后端管理权限管理", notes = "查询权限树形结构-后端管理权限管理")
    public ApiResponse getPermissionTree(){
        return success(permissionService.queryPermissionTree());
    }

    @GetMapping(value = "/web/info/{id}")
    @ApiOperation(value = "获取权限-后端管理权限管理", notes = "获取权限-后端管理权限管理")
    public ApiResponse getPermission(@ApiParam(value = "权限id",required = true)@PathVariable String id){
        if(StringUtils.isBlank(id)){
            return fail("id不能为空");
        }
        PermissionVo permission = permissionService.queryPermissionById(id);
        if (null == permission){
            return fail("未查找到该权限");
        }else{
            return success(permission);
        }
    }

    @GetMapping("/web/user/{id}")
    @ApiOperation(value = "获取用户权限-后端管理权限管理",notes = "获取用户权限-后端管理权限管理")
    public ApiResponse getPermissionByUserId(@ApiParam("用户id") @PathVariable String id) {
        if (StringUtils.isBlank(id)) {
            return fail("id不能为空");
        }
        return success(permissionService.getPermissionByUserId(id));
    }


    @SysLog("新增权限")
    @PostMapping(value = "/web/save")
    @ApiOperation(value = "新增权限管理-后端管理权限管理", notes = "新增权限管理-后端管理权限管理")
    public ApiResponse savePermission(@RequestBody PermissionDto permissionDto){
        if (permissionService.saveOrUpdatePermission(permissionDto)){
            return success("保存成功");
        }else {
            return fail("保存失败");
        }
    }

    @SysLog("修改权限")
    @PutMapping(value = "/web/update")
    @ApiOperation(value = "修改权限管理-后端管理权限管理", notes = "修改权限管理-后端管理权限管理")
    public ApiResponse updatePermission(@RequestBody PermissionDto permissionDto){
        if (permissionService.saveOrUpdatePermission(permissionDto)){
            return success("修改成功");
        }else {
            return fail("修改失败");
        }
    }

    @SysLog("删除权限")
    @DeleteMapping(value = "/web/del/{id}")
    @ApiOperation(value = "删除权限管理-后端管理权限管理", notes = "删除权限管理-后端管理权限管理")
    public ApiResponse deletePermission(@ApiParam(value = "权限id",required = true)@PathVariable String id){
        if(StringUtils.isBlank(id)){
            return fail("id不能为空");
        }
        if (permissionService.deletePermission(id)){
            return success("删除成功");
        }else {
            return fail("删除失败");
        }
    }


    @SysLog("批量删除权限")
    @DeleteMapping(value = "/web/delBatch")
    @ApiOperation(value = "批量删除权限管理-后端管理权限管理", notes = "批量删除权限管理-后端管理权限管理")
    public ApiResponse deleteBatchPermission(@RequestBody String ids){
        if (StringUtils.isBlank(ids)) {
            return fail("参数不能为空");
        }
        List<String> idList = JSON.parseArray(((JSONArray) JSON.parseObject(ids).get("ids")).toJSONString(), String.class);
        if (permissionService.deleteBatchPermission(idList)){
            return success("批量删除成功");
        }else {
            return fail("批量删除失败");
        }
    }
    
    
}
