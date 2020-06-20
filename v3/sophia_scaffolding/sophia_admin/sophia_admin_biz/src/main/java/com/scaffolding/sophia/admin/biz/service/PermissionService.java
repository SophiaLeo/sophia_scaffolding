package com.scaffolding.sophia.admin.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scaffolding.sophia.admin.api.entity.dto.PermissionDto;
import com.scaffolding.sophia.admin.api.entity.vo.PermissionVo;
import com.scaffolding.sophia.common.base.dto.PageDto;

import java.util.List;
import java.util.Map;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.authority
 * @ClassName: AuthorityService
 * @Description:
 * @Version: 1.0
 */
public interface PermissionService {

    /**
     * 根据用户id查询用户的权限
     *
     * @param id 用户id
     * @return List<PermissionVo>
     */
    List<PermissionVo> findAuthorityByUserId(String id);

    /**
     * 根据角色id查询权限
     *
     * @param id 角色id
     * @return List<PermissionVo>
     */
    List<PermissionVo> findAuthorityByRoleId(String id);

    /**
     * 根据用户id获取用户角色所拥有的权限
     *
     * @param id 用户id
     * @return List<Permission>
     */
    Map<String, Object> getPermissionByUserId(String id);
    /**
     * 查询权限分页列表
     *
     * @param param 分页
     * @return IPage<PermissionVo>
     */
    IPage<PermissionVo> queryPermissionList(PageDto param);

    /**
     * 获取权限树形结构
     *
     * @return List<Map < String, Object>>
     */
    List<Map<String, Object>> queryPermissionTree();

    /**
     * 根据权限id获取对应权限
     *
     * @param id 权限id
     * @return PermissionVo
     */
    PermissionVo queryPermissionById(String id);

    /**
     * 保存或修改权限
     *
     * @param permissionDto 权限信息
     * @return boolean
     */
    boolean saveOrUpdatePermission(PermissionDto permissionDto);

    /**
     * 逻辑删除权限
     *
     * @param id 权限id
     * @return boolean
     */
    boolean deletePermission(String id);

    /**
     * 批量逻辑删除权限
     *
     * @param ids 权限ids
     * @return boolean
     */
    boolean deleteBatchPermission(List<String> ids);

    /**
     * 获取某角色拥有的权限
     *
     * @param id 角色id
     * @return List<String>
     */
    List<String> getPermissionByRoleId(String id);

    /**
     * 角色分配权限
     *
     * @param id   角色id
     * @param list 权限id
     * @return boolean
     */
    boolean savePermissionAndRole(String id, List<String> list);
}
