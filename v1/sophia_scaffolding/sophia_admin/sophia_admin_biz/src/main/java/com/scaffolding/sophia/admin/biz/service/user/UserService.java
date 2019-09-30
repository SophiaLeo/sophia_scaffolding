package com.scaffolding.sophia.admin.biz.service.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scaffolding.sophia.admin.api.entity.user.User;
import com.scaffolding.sophia.admin.api.vo.UserVo;
import com.scaffolding.sophia.admin.biz.dao.user.UserDao;
import com.scaffolding.sophia.common.base.constants.GlobalsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.user
 * @ClassName: UserService
 * @Description:
 * @Version: 1.0
 */
@Service
public class UserService extends ServiceImpl<UserDao, User> {

    @Autowired
    private UserDao userDao;

    public User loadUserByUserName(String username){
        return userDao.findByUserName(username);
    }

    @Cacheable(value= GlobalsConstants.REDIS_USER_CACHE,unless = "#result == null", key="T(com.scaffolding.sophia.common.base.constants.GlobalsConstants).USER_KEY_PREFIX.concat(T(String).valueOf(#userId))")
    public User loadUserByUserId(Long userId){
        return userDao.findByUserId(userId);
    }


    public List<UserVo> findUserVoList(UserVo userVo) {
        return userDao.findUserVoList(userVo);
    }

}
