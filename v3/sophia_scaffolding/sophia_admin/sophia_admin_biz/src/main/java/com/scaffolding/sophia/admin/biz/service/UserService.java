package com.scaffolding.sophia.admin.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scaffolding.sophia.admin.api.entity.bo.User;
import com.scaffolding.sophia.admin.api.entity.dto.UserDto;
import com.scaffolding.sophia.admin.api.entity.dto.UserSearchDto;
import com.scaffolding.sophia.admin.api.entity.vo.UserVo;

import java.util.List;
import java.util.Map;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service
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
     * @return UserVo
     */
    UserVo loadUserByUserId(String userId);

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
     * @param username  用户名
     * @param password  密码
     * @param code      验证码
     * @param randomStr 获取reids验证码key后缀
     * @return UserVo
     */
    UserVo loginByPassword(String username, String password, String code, String randomStr);

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
    boolean deleteUser(String id);

    /**
     * 分页条件查询
     *
     * @param userSearchDto 条件参数
     * @return IPage<UserVo>
     */
    IPage<UserVo> queryUserList(UserSearchDto userSearchDto);

    /**
     * 修改用户状态
     *
     * @param id     用户id
     * @param status 状态  0无效 1有效
     * @return boolean
     */
    boolean updateStatus(String id, Integer status);

    /**
     * 保存用户
     *
     * @param userDto 用户
     * @return boolean
     */
    boolean saveUser(UserDto userDto);

    /**
     * 修改用户
     *
     * @param userDto 用户
     * @return boolean
     */
    boolean updateUser(UserDto userDto);

    /**
     * 获取token
     *
     * @param username  用户名
     * @param password  密码
     * @param code      验证码
     * @param randomStr 获取redis验证码的key后缀
     * @return String
     */
    String getToken(String username, String password, String code, String randomStr);

    /**
     * 判断用户原密码是否正确
     *
     * @param id       用户id
     * @param password 密码
     * @return boolean
     */
    boolean judgePassword(String id, String password);

    /**
     * 批量逻辑删除用户
     *
     * @param idList 用户id
     * @return boolean
     */
    boolean deleteBatchUser(List<String> idList);

    /**
     * 修改用户密码
     *
     * @param userDto 用户信息
     * @return boolean
     */
    boolean updatePassword(UserDto userDto);

    /**
     * 判断用户名称是否重复
     *
     * @param username 用户名称
     * @param id       用户id
     * @return Map<String, Object>
     */
    Map<String, Object> checkUserName(String username, String id);

    /**
     * 判断用户昵称是否重复
     *
     * @param nickname 用户昵称
     * @param id       用户id
     * @return Map<String, Object>
     */
    Map<String, Object> checkNickname(String nickname, String id);

    /**
     * 判断手机是否重复
     *
     * @param phone 手机
     * @param id    用户id
     * @return Map<String, Object>
     */
    Map<String, Object> checkPhone(String phone, String id);

    /**
     * 分配角色
     *
     * @param userDto 用户信息
     * @return boolean
     */
    boolean updateRole(UserDto userDto);
}