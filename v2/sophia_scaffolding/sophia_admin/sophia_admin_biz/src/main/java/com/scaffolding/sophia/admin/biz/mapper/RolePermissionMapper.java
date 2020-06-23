package com.scaffolding.sophia.admin.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scaffolding.sophia.admin.api.entity.bo.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LHL
 */
@Repository
public interface RolePermissionMapper extends BaseMapper<RolePermission> {


    /**
     * 根据角色id查询权限
     *
     * @param id 角色id
     * @return List<RolePermission>
     */
    List<RolePermission> selectByRoleId(String id);

    /**
     * 根据权限id获取关联表的id
     *
     * @param list 权限ids
     * @return List<RolePermission>
     */
    List<RolePermission> selectBatchPerIds(@Param("list") List<String> list);

    /**
     * 根据角色id批量删除
     *
     * @param list 角色id
     * @return int
     */
    int deleteBatchByRoleIds(@Param("list") List<String> list);

    /**
     * 角色分配权限
     *
     * @param list 角色权限id
     * @return int
     */
    int insertBatch(@Param("list") List<RolePermission> list);
}