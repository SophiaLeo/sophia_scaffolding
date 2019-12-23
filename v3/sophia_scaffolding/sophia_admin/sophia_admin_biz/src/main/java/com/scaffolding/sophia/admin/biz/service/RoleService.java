package com.scaffolding.sophia.admin.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scaffolding.sophia.admin.api.entity.bo.Role;
import com.scaffolding.sophia.admin.api.entity.dto.RoleDto;
import com.scaffolding.sophia.admin.api.entity.vo.RoleVo;

import java.util.List;
import java.util.Map;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.role
 * @ClassName: RoleService
 * @Description:
 * @Version: 1.0
 */
public interface RoleService {


    /**
     * 根据用户id查询用户的角色
     *
     * @param userId 用户id
     * @return RoleVo
     */
    Role getRoleByUserId(String userId);



    /**
     * 角色分页列表查询
     *
     * @param param 分页
     * @return IPage<RoleVo>
     */
    IPage<RoleVo> queryRoleList(Map<String,Object> param);

    /**
     * 获取角色信息
     *
     * @param id 角色id
     * @return RoleVo
     */
    RoleVo queryRoleById(String id);

    /**
     * 保存或修改角色
     *
     * @param roleDto 角色
     * @return Map<String, Object>
     */
    Map<String, Object> saveOrUpdateRole(RoleDto roleDto);

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return Map<String, Object>
     */
    Map<String, Object> deleteRole(String id);

    /**
     * 批量删除角色
     *
     * @param ids 角色id
     * @return Map<String, Object>
     */
    Map<String, Object> deleteBatchRole(List<String> ids);


    /**
     * 角色列表
     *
     * @return RoleVo
     * @param userId
     */
    List<RoleVo> queryRole(String userId);
}
