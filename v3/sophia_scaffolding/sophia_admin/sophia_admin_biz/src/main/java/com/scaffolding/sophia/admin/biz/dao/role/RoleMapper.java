package com.scaffolding.sophia.admin.biz.dao.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scaffolding.sophia.admin.api.entity.bo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
    Role getRoleByUserId(@Param(value = "userId") Long userId);
}
