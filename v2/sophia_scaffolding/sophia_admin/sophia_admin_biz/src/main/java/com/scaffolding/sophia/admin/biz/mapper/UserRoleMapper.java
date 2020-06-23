package com.scaffolding.sophia.admin.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scaffolding.sophia.admin.api.entity.bo.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LHL
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 删除用户角色关联
     *
     * @param list 用户ids
     * @return int
     */
    int deleteBatchByUserIds(@Param("list") List<String> list);
    /**
     * 根据角色id批量删除
     *
     * @param list 角色id
     * @return int
     */
    int deleteBatchByRoleIds(@Param("list") List<String> list);
}