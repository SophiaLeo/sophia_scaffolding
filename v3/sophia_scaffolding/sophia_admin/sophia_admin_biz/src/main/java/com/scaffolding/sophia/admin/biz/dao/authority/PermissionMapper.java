package com.scaffolding.sophia.admin.biz.dao.authority;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scaffolding.sophia.admin.api.entity.authority.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.dao.authority
 * @ClassName: PermissionMapper
 * @Date: 2019/11/5 10:59
 * @Description:
 * @Version: 1.0
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {


    /**
     * 根据用户id查询用户的权限
     *
     * @param userId 用户id
     * @return List<Permission>
     */
    List<Permission> findAuthorityByUserId(@Param(value = "userId") Long userId);

    /**
     * 根据角色id查询权限
     *
     * @param id 角色id
     * @return List<Permission>
     */
    List<Permission> findAuthorityByRoleId(@Param("id") Long id);

}