package com.scaffolding.sophia.common.security.model;

import com.scaffolding.sophia.common.base.constants.BizConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.security.model
 * @ClassName: LoginUser
 * @Description:
 * @Version: 1.0
 */
public class LoginUser implements UserDetails {

    private Long id;
    private Integer status;
    private String nickname;
    private String password;
    private String username;
    private Long deptId;
    private Long compId;
    private List<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public LoginUser() {
        super();
    }

    public LoginUser(String username, String password,String nickname,Integer status, List<GrantedAuthority> authorities) {
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.authorities = authorities;
        this.status = status;
    }

    public LoginUser(Long id, Integer status, String nickname, String password, String username, Long deptId, Long compId, List<GrantedAuthority> authorities, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.id = id;
        this.status = status;
        this.nickname = nickname;
        this.password = password;
        this.username = username;
        this.deptId = deptId;
        this.compId = compId;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    public LoginUser(Long id, Long deptId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, List<GrantedAuthority> authorities) {
        this.id = id;
        this.deptId = deptId;
        this.password = password;
        this.username = username;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public boolean isAccountNonExpired() {
        return !BizConstants.USER_STATUS_EXPIRED.equals(this.status);
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return !BizConstants.USER_STATUS_LOCKED.equals(this.status);
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return BizConstants.USER_STATUS_NORMAL.equals(this.status);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
