package com.scaffolding.sophia.admin.biz.service.user;

import com.scaffolding.sophia.admin.api.entity.user.User;
import com.scaffolding.sophia.admin.api.vo.UserVo;

import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.user
 * @ClassName: UserService
 * @Description:
 * @Version: 1.0
 */
public interface UserService {

    /**
     * 根据用户名称查询用户
     *
     * @param username 用户名
     * @return User
     */
    User loadUserByUserName(String username);

    /**
     * 根据用户id查询用户
     *
     * @param userId 用户id
     * @return User
     */
    User loadUserByUserId(Long userId);

    /**
     * 根据用户名称查询所有相同用户名的用户
     *
     * @param username 用户名
     * @return List<UserVo>
     */
    List<UserVo> findUserVoList(String username);
    /**
     * 用户 用户名和密码登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return UserVo
     * */
    UserVo loginByPassword(String userName, String password);
}
