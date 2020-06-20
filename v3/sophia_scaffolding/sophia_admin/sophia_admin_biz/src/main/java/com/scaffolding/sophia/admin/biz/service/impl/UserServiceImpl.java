package com.scaffolding.sophia.admin.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scaffolding.sophia.admin.api.entity.bo.Role;
import com.scaffolding.sophia.admin.api.entity.bo.User;
import com.scaffolding.sophia.admin.api.entity.bo.UserRole;
import com.scaffolding.sophia.admin.api.entity.dto.UserDto;
import com.scaffolding.sophia.admin.api.entity.dto.UserSearchDto;
import com.scaffolding.sophia.admin.api.entity.vo.UserVo;
import com.scaffolding.sophia.admin.biz.mapper.RoleMapper;
import com.scaffolding.sophia.admin.biz.mapper.UserMapper;
import com.scaffolding.sophia.admin.biz.mapper.UserRoleMapper;
import com.scaffolding.sophia.admin.biz.service.UserService;
import com.scaffolding.sophia.common.base.constants.BizConstants;
import com.scaffolding.sophia.common.base.constants.GlobalsConstants;
import com.scaffolding.sophia.common.base.exception.CommonException;
import com.scaffolding.sophia.common.security.properties.SecurityOAuth2ClientProperties;
import com.scaffolding.sophia.common.security.util.UserUtils;
import com.scaffolding.sophia.common.util.HttpCallOtherInterfaceUtils;
import com.scaffolding.sophia.common.util.UuidUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.user.impl
 * @ClassName: UserServiceImpl
 * @Date: 2019/11/5 09:19
 * @Description:
 * @Version: 1.0
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${gateway.url}")
    private String url;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityOAuth2ClientProperties securityOAuth2ClientProperties;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User loadUserByUserName(String username) {
        return baseMapper.findByUserName(username);
    }

    @Override
    @Cacheable(value = GlobalsConstants.REDIS_USER_CACHE, unless = "#result == null", key = "T(com.scaffolding.sophia.common.base.constants.GlobalsConstants).USER_KEY_PREFIX.concat(T(String).valueOf(#userId))")
    public UserVo loadUserByUserId(String userId) {
        return new UserVo().buildVo(baseMapper.selectById(userId));
    }

    @Override
    public boolean judgePassword(String id, String password) {
        User user = baseMapper.selectById(id);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }


    @Override
    public List<UserVo> findUserVoList(String username) {
        return baseMapper.findUserVoList(username);
    }


    @Override
    public UserVo loginByPassword(String username, String password, String code, String randomStr) {
        User user = baseMapper.findByUserName(username);
        if (null == user) {
            return null;
        }
        String s;
        //数据库密码是加密了的
        if (passwordEncoder.matches(password, user.getPassword())) {
            s = "?client_id=" + securityOAuth2ClientProperties.getClientId() + "&client_secret=" + securityOAuth2ClientProperties.getClientSecret() + "&grant_type=password&scope=all&username=" + username + "&password=" + password + "&code=" + code + "&randomStr=" + randomStr;
            String sr = HttpCallOtherInterfaceUtils.callOtherInterface(url, "/api/auth/oauth/token" + s);
            Map srmap = JSON.parseObject(sr);
            if (null == srmap) {
                throw new CommonException("认证失败");
            }
            String access_token;
            if (StringUtils.isEmpty((String) srmap.get("access_token"))) {
                access_token = (String) srmap.get("value");
            } else {
                access_token = (String) srmap.get("access_token");
            }
            UserVo vo = new UserVo();
            BeanUtils.copyProperties(user, vo);
            vo.setAccessToken(access_token);
            return vo;
        }
        return null;
    }

    @CacheEvict(value = GlobalsConstants.REDIS_USER_CACHE, key = "T(com.scaffolding.sophia.common.base.constants.GlobalsConstants).USER_KEY_PREFIX.concat(T(String).valueOf(#user.id))")
    @Transactional(rollbackFor = CommonException.class)
    @Override
    public boolean updateUserInfo(User user) {
        return baseMapper.updateById(user) > 0;
    }

    @Override
    @Transactional(rollbackFor = CommonException.class)
    @CacheEvict(value = GlobalsConstants.REDIS_USER_CACHE, key = "T(com.scaffolding.sophia.common.base.constants.GlobalsConstants).USER_KEY_PREFIX.concat(T(String).valueOf(#id))")
    public boolean deleteUser(String id) {
        User user = new User();
        user.setId(id);
        user.setIsDeleted(BizConstants.SYS_YES);
        user.setUpdateTime(LocalDateTime.now());
        user.setUpdateUser(UserUtils.getLoginUser().getId());
        int i = baseMapper.updateById(user);
        int b = userRoleMapper.delete(new QueryWrapper<UserRole>().eq("USER_ID", id));
        //TODO 删除头像
        return i > 0 && b > 0;
    }

    @Transactional(rollbackFor = CommonException.class)
    @Override
    public boolean deleteBatchUser(List<String> ids) {
        try {
            ids.forEach(this::deleteUser);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        int b = userRoleMapper.deleteBatchByUserIds(ids);
        return b > 0;
    }

    @Transactional(rollbackFor = CommonException.class)
    @Override
    public boolean updatePassword(UserDto userDto) {
        if (StringUtils.isBlank(userDto.getUserId())) {
            userDto.setUserId(UserUtils.getLoginUser().getId());
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUpdateTime(LocalDateTime.now());
        user.setUpdateUser(userDto.getUserId());
        return baseMapper.updateById(user) > 0;
    }


    @Override
    public IPage<UserVo> queryUserList(UserSearchDto userSearchDto) {
        String deptId = UserUtils.getLoginUser().getDeptId();
        if(StringUtils.isBlank(deptId)){
            deptId =  UserUtils.getLoginUser().getCompId();
        }
        userSearchDto.setDeptPid(deptId);
        if (StringUtils.isBlank(userSearchDto.getUserId())) {
            userSearchDto.setUserId(UserUtils.getLoginUser().getId());
        }
        Role role = roleMapper.getRoleByUserId(UserUtils.getLoginUser().getId());
        if (null != role) {
            userSearchDto.setRoleCode(role.getRoleCode());
        }
        Integer currentPage = userSearchDto.getCurrentPage() == null ? 1 : userSearchDto.getCurrentPage();
        Integer pageSize = userSearchDto.getPageSize() == null ? 10 : userSearchDto.getPageSize();
        Page<UserVo> page = new Page<>(currentPage, pageSize);
        return page.setRecords(baseMapper.findUserList(page, userSearchDto));
    }

    @Override
    @Transactional(rollbackFor = CommonException.class)
    @CacheEvict(value = GlobalsConstants.REDIS_USER_CACHE, key = "T(com.scaffolding.sophia.common.base.constants.GlobalsConstants).USER_KEY_PREFIX.concat(T(String).valueOf(#id))")
    public boolean updateStatus(String id, Integer status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdateTime(LocalDateTime.now());
        user.setUpdateUser(UserUtils.getLoginUser().getId());
        return baseMapper.updateById(user) > 0;
    }

    @Transactional(rollbackFor = CommonException.class)
    @Override
    public boolean saveUser(UserDto userDto) {
        if (StringUtils.isBlank(userDto.getUserId())) {
            userDto.setUserId(UserUtils.getLoginUser().getId());
        }
        User user = new User();
        user.buildBo(userDto);
        user.setId(UuidUtils.getUuid());
        //TODO 保存头像
        user.setCreateTime(LocalDateTime.now());
        user.setCreateUser(userDto.getUserId());
        user.setUserType(BizConstants.HT);
        user.setIsDeleted(BizConstants.SYS_NO);
        user.setStatus(BizConstants.USER_STATUS_NORMAL);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (StringUtils.isNotBlank(userDto.getRoleId())) {
            userDto.setId(user.getId());
            this.updateRole(userDto);
        }
        return baseMapper.insert(user) > 0;
    }

    @Transactional(rollbackFor = CommonException.class)
    @CacheEvict(value = GlobalsConstants.REDIS_USER_CACHE, key = "T(com.scaffolding.sophia.common.base.constants.GlobalsConstants).USER_KEY_PREFIX.concat(T(String).valueOf(#userDto.id))")
    @Override
    public boolean updateUser(UserDto userDto) {
        if (StringUtils.isBlank(userDto.getUserId())) {
            userDto.setUserId(UserUtils.getLoginUser().getId());
        }
        User user = new User();
        user.buildBo(userDto);
        User ckUser = baseMapper.selectById(user.getId());
        if (StringUtils.isNotBlank(userDto.getPassword())) {
            // 判断密码是否被修改
            if (!user.getPassword().equals(ckUser.getPassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }
        //TODO 是否修改头像
        user.setUpdateTime(LocalDateTime.now());
        user.setUpdateUser(UserUtils.getLoginUser().getId());
        if (StringUtils.isNotBlank(userDto.getRoleId())) {
            userDto.setId(user.getId());
            this.updateRole(userDto);
        }
        return baseMapper.updateById(user) > 0;
    }

    @Override
    public String getToken(String username, String password, String code, String randomStr) {
        User user = baseMapper.findByUserName(username);
        if (null == user) {
            return null;
        }
        String access_token = "";
        String s;
        //数据库密码是加密了的
        if (passwordEncoder.matches(password, user.getPassword())) {
            s = "?client_id=" + securityOAuth2ClientProperties.getClientId() + "&client_secret=" + securityOAuth2ClientProperties.getClientSecret() + "&grant_type=password&scope=all&username=" + username + "&password=" + password + "&code=" + code + "&randomStr=" + randomStr;
            String sr = HttpCallOtherInterfaceUtils.callOtherInterface(url, "/api/auth/oauth/token" + s);
            Map srmap = JSON.parseObject(sr);
            if (null == srmap) {
                throw new CommonException("认证失败");
            }
            if (StringUtils.isEmpty((String) srmap.get("access_token"))) {
                access_token = (String) srmap.get("value");
            } else {
                access_token = (String) srmap.get("access_token");
            }

        }
        return access_token;
    }

    @Override
    public Map<String, Object> checkUserName(String username, String id) {
        Map<String, Object> map = new HashMap<>(16);
        if (StringUtils.isBlank(id)) {
            List<User> users = baseMapper.selectList(new QueryWrapper<User>().eq("USERNAME", username).eq("STATUS", BizConstants.SYS_YES).eq("USER_TYPE", BizConstants.SYS_YES));
            if (users.size() > 0) {
                map.put("flag", false);
                map.put("msg", "用户登录名不能重复");
                return map;
            }
        } else {
            List<User> users = baseMapper.selectList(new QueryWrapper<User>().ne("ID", id).eq("USERNAME", username).eq("STATUS", BizConstants.SYS_YES).eq("USER_TYPE", BizConstants.SYS_YES));
            if (users.size() > 0) {
                map.put("flag", false);
                map.put("msg", "用户登录名不能重复");
                return map;
            }
        }
        map.put("flag", true);
        return map;
    }

    @Override
    public Map<String, Object> checkNickname(String nickname, String id) {
        Map<String, Object> map = new HashMap<>(16);
        if (StringUtils.isBlank(id)) {
            List<User> users = baseMapper.selectList(new QueryWrapper<User>().eq("NICKNAME", nickname).eq("STATUS", BizConstants.SYS_YES).eq("USER_TYPE", BizConstants.SYS_YES));
            if (users.size() > 0) {
                map.put("flag", false);
                map.put("msg", "用户昵称不能重复");
                return map;
            }
        } else {
            List<User> users = baseMapper.selectList(new QueryWrapper<User>().ne("ID", id).eq("NICKNAME", nickname).eq("STATUS", BizConstants.SYS_YES).eq("USER_TYPE", BizConstants.SYS_YES));
            if (users.size() > 0) {
                map.put("flag", false);
                map.put("msg", "用户昵称不能重复");
                return map;
            }
        }
        map.put("flag", true);
        return map;
    }


    @Override
    public Map<String, Object> checkPhone(String phone, String id) {
        Map<String, Object> map = new HashMap<>(16);
        if (StringUtils.isBlank(id)) {
            List<User> userList = baseMapper.selectList(new QueryWrapper<User>().eq("PHONE", phone).eq("STATUS", BizConstants.SYS_YES).eq("USER_TYPE", BizConstants.SYS_YES));
            if (userList.size() > 0) {
                map.put("flag", false);
                map.put("msg", "手机号不能重复");
                return map;
            }
        } else {
            List<User> userList = baseMapper.selectList(new QueryWrapper<User>().ne("ID", id).eq("PHONE", phone).eq("STATUS", BizConstants.SYS_YES).eq("USER_TYPE", BizConstants.SYS_YES));
            if (userList.size() > 0) {
                map.put("flag", false);
                map.put("msg", "手机号不能重复");
                return map;
            }
        }
        map.put("flag", true);
        return map;
    }

    @Override
    public boolean updateRole(UserDto userDto) {
        UserRole userRole = userRoleMapper.selectOne(new QueryWrapper<UserRole>().eq("USER_ID", userDto.getId()));
        if (null == userRole) {
            UserRole userRole1 = new UserRole();
            userRole1.setId(UuidUtils.getUuid());
            userRole1.setUserId(userDto.getId());
            userRole1.setRoleId(userDto.getRoleId());
            return userRoleMapper.insert(userRole1) > 0;
        } else {
            if (!(userRole.getRoleId().equals(userDto.getRoleId()))) {
                userRole.setRoleId(userDto.getRoleId());
                return userRoleMapper.updateById(userRole) > 0;
            }
            return true;
        }
    }

}