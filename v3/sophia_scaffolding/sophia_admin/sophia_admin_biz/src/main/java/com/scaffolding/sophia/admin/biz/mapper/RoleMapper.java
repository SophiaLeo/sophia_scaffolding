package com.scaffolding.sophia.admin.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scaffolding.sophia.admin.api.entity.bo.Role;
import com.scaffolding.sophia.admin.api.entity.vo.RoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.dao.role
 * @ClassName: RoleMapper
 * @Description:
 * @Version: 1.0
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询用户的角色
     *
     * @param userId 用户id
     * @return Role
     */
    Role getRoleByUserId(@Param(value = "userId") String userId);


    /**
     * 分页 列表查询
     *
     * @param page 分页
     * @return List<RoleVo>
     */
    List<RoleVo> selectRoleList(Page<RoleVo> page);

    /**
     * 查询
     * @param roleCode 当前登录用户角色编码
     * @return List<RoleVo>
     */
    List<RoleVo> selectRole(@Param("roleCode") String roleCode);

    /**
     * 批量逻辑删除
     *
     * @param list 角色id
     * @return int
     */
    int updateBatchByIds(@Param("list") List<String> list);
}
