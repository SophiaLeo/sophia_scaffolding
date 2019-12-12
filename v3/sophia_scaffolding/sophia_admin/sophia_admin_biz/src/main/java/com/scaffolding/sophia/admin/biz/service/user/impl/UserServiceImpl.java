package com.scaffolding.sophia.admin.biz.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scaffolding.sophia.admin.api.entity.user.User;
import com.scaffolding.sophia.admin.api.vo.UserVo;
import com.scaffolding.sophia.admin.biz.dao.user.UserMapper;
import com.scaffolding.sophia.admin.biz.service.user.UserService;
import com.scaffolding.sophia.common.base.constants.BizConstants;
import com.scaffolding.sophia.common.base.constants.GlobalsConstants;
import com.scaffolding.sophia.common.base.exception.CommonException;
import com.scaffolding.sophia.common.security.properties.SecurityOAuth2ClientProperties;
import com.scaffolding.sophia.common.security.util.UserUtils;
import com.scaffolding.sophia.common.util.HttpCallOtherInterfaceUtils;
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

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Value("${gateway.url}")
    private String url;

    @Autowired
    private SecurityOAuth2ClientProperties securityOAuth2ClientProperties;

    @Override
    public User loadUserByUserName(String username) {
        return baseMapper.findByUserName(username);
    }

    @Override
    @Cacheable(value = GlobalsConstants.REDIS_USER_CACHE, unless = "#result == null", key = "T(com.scaffolding.sophia.common.base.constants.GlobalsConstants).USER_KEY_PREFIX.concat(T(String).valueOf(#userId))")
    public User loadUserByUserId(Long userId) {
        return baseMapper.selectById(userId);
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
    public boolean deleteUser(Long id) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        //TODO 删除头像
        wrapper.set("is_deleted", BizConstants.SYS_YES);
        wrapper.eq("id", id);
        return this.update(new User(), wrapper);
    }

    @Override
    public IPage<UserVo> queryUserList(Map param) {
        Integer currentPage = param.get("currentPage") == null ? 1 : Integer.parseInt(String.valueOf(param.get("currentPage")));
        Integer pageSize = param.get("pageSize") == null ? 10 : Integer.parseInt(String.valueOf(param.get("pageSize")));
        Page<UserVo> page = new Page<>(currentPage, pageSize);
        return page.setRecords(baseMapper.findUserList(page, param));
    }

    @Override
    @Transactional(rollbackFor = CommonException.class)
    @CacheEvict(value = GlobalsConstants.REDIS_USER_CACHE, key = "T(com.scaffolding.sophia.common.base.constants.GlobalsConstants).USER_KEY_PREFIX.concat(T(String).valueOf(#id))")
    public boolean updateStatus(Long id, Integer status) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("status", status);
        wrapper.eq("id", id);
        return update(new User(), wrapper);
    }

    @Transactional(rollbackFor = CommonException.class)
    @Override
    public boolean saveUser(User user) {
        //TODO 保存头像
        user.setCreateTime(LocalDateTime.now());
        user.setCreateUser(UserUtils.getLoginUser().getId());
        user.setIsDeleted(BizConstants.SYS_NO);
        user.setStatus(BizConstants.USER_STATUS_NORMAL);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return baseMapper.insert(user) > 0;
    }

    @Transactional(rollbackFor = CommonException.class)
    @CacheEvict(value = GlobalsConstants.REDIS_USER_CACHE, key = "T(com.scaffolding.sophia.common.base.constants.GlobalsConstants).USER_KEY_PREFIX.concat(T(String).valueOf(#user.id))")
    @Override
    public boolean updateUser(User user) {
        User ckUser = loadUserByUserId(user.getId());
        // 判断密码是否被修改
        if (!user.getPassword().equals(ckUser.getPassword())) {
            // if (!(passwordEncoder.matches(user.getPassword(), ckUser.getPassword()))) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        //TODO 是否修改头像
        user.setUpdateTime(LocalDateTime.now());
        user.setUpdateUser(UserUtils.getLoginUser().getId());
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


}