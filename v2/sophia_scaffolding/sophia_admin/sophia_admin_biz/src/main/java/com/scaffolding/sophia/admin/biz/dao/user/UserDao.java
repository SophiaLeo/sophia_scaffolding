package com.scaffolding.sophia.admin.biz.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scaffolding.sophia.admin.api.entity.user.User;
import com.scaffolding.sophia.admin.api.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.dao.user
 * @ClassName: UserDao
 * @Description:
 * @Version: 1.0
 */
@Repository
public interface UserDao extends BaseMapper<User> {

    /**
     * 根据用户名称查询用户
     * @param username 用户名称
     * @return User
     */
    User findByUserName(@Param(value = "username") String username);

    /**
     * 根据用户id查询用户
     * @param userId 用户id
     * @return User
     */
    User findByUserId(@Param(value = "userId") Long userId);

    /**
     * 根据用户名称查询所有相同用户名的用户
     *
     * @param userVo 用户
     * @return List<UserVo>
     */
    List<UserVo> findUserVoList(UserVo userVo);
}
