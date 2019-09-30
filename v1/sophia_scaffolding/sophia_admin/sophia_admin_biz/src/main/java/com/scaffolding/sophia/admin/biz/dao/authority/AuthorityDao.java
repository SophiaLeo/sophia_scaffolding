package com.scaffolding.sophia.admin.biz.dao.authority;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scaffolding.sophia.admin.api.entity.authority.Authority;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.dao.authority
 * @ClassName: AuthorityDao
 * @Description:
 * @Version: 1.0
 */
@Repository
public interface AuthorityDao extends BaseMapper<Authority> {

    /**
     * 根据用户id查询用户的权限
     * @param userId 用户id
     * @return List<Authority>
     */
    List<Authority> findAuthorityByUserId(@Param(value = "userId") Long userId);
}
