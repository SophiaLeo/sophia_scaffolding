package com.scaffolding.sophia.admin.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scaffolding.sophia.admin.api.entity.bo.Permission;
import com.scaffolding.sophia.admin.api.entity.vo.PermissionVo;
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
     * @return List<PermissionVo>
     */
    List<PermissionVo> findAuthorityByUserId(@Param(value = "userId") String userId);

    /**
     * 根据角色id查询权限
     *
     * @param id 角色id
     * @return List<PermissionVo>
     */
    List<PermissionVo> findAuthorityByRoleId(@Param("id") String id);

    /**
     * 根据用户名称查询该用户的权限
     *
     * @param userId 用户id
     * @return List<Permission>
     */
    List<PermissionVo> getPermissionByUserId(String userId);

    /**
     * 查询权限分页列表
     *
     * @param page 分页
     * @return List<PermissionVo>
     */
    List<PermissionVo> selectPermissionList(Page<PermissionVo> page);

    /**
     * 逻辑删除权限,修改status为0
     *
     * @param list 权限ids
     * @return int
     */
    int updateBatchIds(@Param("list") List<String> list);

    /**
     * 获取某角色的权限
     *
     * @param id 角色id
     * @return List<Permission>
     */
    List<Permission> selectByRoleId(String id);
}