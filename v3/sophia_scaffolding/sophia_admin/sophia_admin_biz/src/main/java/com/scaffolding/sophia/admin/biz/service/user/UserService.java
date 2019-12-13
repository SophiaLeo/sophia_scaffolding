package com.scaffolding.sophia.admin.biz.service.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scaffolding.sophia.admin.api.entity.bo.User;
import com.scaffolding.sophia.admin.api.entity.vo.UserVo;

import java.util.List;
import java.util.Map;

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
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param randomStr 获取reids验证码key后缀
     * @return UserVo
     */
    UserVo loginByPassword(String username, String password, String code,String randomStr);

    /**
     * 修改用户
     *
     * @param user 用户信息
     * @return boolean
     */
    boolean updateUserInfo(User user);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return boolean
     */
    boolean deleteUser(Long id);

    /**
     * 分页条件查询
     *
     * @param param 条件参数
     * @return IPage<UserVo>
     */
    IPage<UserVo> queryUserList(Map param);

    /**
     * 修改用户状态
     *
     * @param id     用户id
     * @param status 状态  0无效 1有效
     * @return boolean
     */
    boolean updateStatus(Long id, Integer status);

    /**
     * 保存用户
     *
     * @param user     用户
     * @return boolean
     */
    boolean saveUser(User user);
    /**
     * 修改用户
     *
     * @param user     用户
     * @return boolean
     */
    boolean updateUser(User user);

    /**
     * 获取token
     *
     * @return String
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param randomStr 获取redis验证码的key后缀
     */
    String getToken(String username, String password, String code, String randomStr);
}