package com.scaffolding.sophia.common.security.util;

import com.scaffolding.sophia.common.security.model.LoginUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.security.util
 * @ClassName: UserUtils
 * @Date: 2019/11/5 09:09
 * @Description:
 * @Version: 1.0
 */
public class UserUtils {

    public static LoginUser getLoginUser() {
        //判断是否可以转换
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Auth = (OAuth2Authentication) authentication;
            authentication = oAuth2Auth.getUserAuthentication();
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
                // Map map = (Map) authenticationToken.getDetails();
                // map = (Map) map.get("principal");
                // return JSONObject.parseObject(JSONObject.toJSONString(map), LoginUser.class);
                return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            }
        }
        return null;
    }
}
