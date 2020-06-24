package com.scaffolding.sophia.admin.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scaffolding.sophia.admin.api.entity.bo.User;
import com.scaffolding.sophia.admin.api.entity.dto.UserSearchDto;
import com.scaffolding.sophia.admin.api.entity.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.dao.user
 * @ClassName: UserMapper
 * @Description:
 * @Version: 1.0
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名(或手机号或邮箱)查询用户
     *
     * @param username 用户名(或手机号或邮箱)
     * @return User
     */
    User findByUserName(@Param(value = "username") String username);

    /**
     * 根据用户id查询用户
     *
     * @param userId 用户id
     * @return User
     */
    User findByUserId(@Param(value = "userId") String userId);

    /**
     * 根据用户名称查询所有相同用户名的用户
     *
     * @param username 用户名
     * @return List<UserVo>
     */
    List<UserVo> findUserVoList(@Param("username") String username);

    /**
     * 条件分页查询
     *
     * @param page  分页
     * @param param 条件参数
     * @return List<UserVo>
     */
    List<UserVo> findUserList(Page page, @Param("param") UserSearchDto param);
}
