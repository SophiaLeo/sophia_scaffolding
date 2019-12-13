package com.scaffolding.sophia.common.security.service;

import com.alibaba.fastjson.JSON;
import com.scaffolding.sophia.admin.api.entity.bo.Permission;
import com.scaffolding.sophia.admin.api.entity.bo.User;
import com.scaffolding.sophia.admin.api.feign.client.AuthorityClient;
import com.scaffolding.sophia.admin.api.feign.client.UserClient;
import com.scaffolding.sophia.common.base.constants.BizConstants;
import com.scaffolding.sophia.common.base.exception.CommonException;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.security.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.security.service
 * @ClassName: SophiaUserDetailService
 * @Description: 用户登录 查询登录用户
 * @Version: 1.0
 */
@Component
public class SophiaUserDetailService implements UserDetailsService {

    @Autowired
    private UserClient userClient;
    @Autowired
    private AuthorityClient authorityClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(username)){
            throw new CommonException("登录名不能为空");
        }
        ApiResponse apiResponse = userClient.getUserByUserName(username);
        User user = JSON.parseObject(JSON.toJSONString( apiResponse.getData(), true),User.class);
        if (user == null) {
            throw new CommonException("登录名不存在");
        } else if (BizConstants.USER_STATUS_EXPIRED.equals(user.getStatus())) {
            throw new CommonException("用户已过期");
        } else if (BizConstants.USER_STATUS_LOCKED.equals(user.getStatus())) {
            throw new CommonException("用户已锁定");
        } else if (BizConstants.USER_STATUS_UNUSED.equals(user.getStatus())) {
            throw new CommonException("用户已禁用");
        }
        ApiResponse response = authorityClient.getAuthorityByUserId(user.getId());
        List<Permission> authList = JSON.parseArray(JSON.toJSONString(response.getData(), true),Permission.class);
        List<GrantedAuthority> lists = new ArrayList<>();
        if(authList != null && authList.size()>0){
            for (Permission auth : authList) {
                lists.add(new SimpleGrantedAuthority(auth.getCode()));
            }
        }
        LoginUser loginUser = new LoginUser(username,user.getPassword(),user.getNickname(),user.getStatus(), lists);
        loginUser.setId(user.getId());
        loginUser.setDeptId(user.getDeptId());
        loginUser.setCompId(user.getCompId());
        return loginUser;
    }
}
