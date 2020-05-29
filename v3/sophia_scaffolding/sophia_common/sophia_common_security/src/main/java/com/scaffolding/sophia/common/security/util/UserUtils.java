package com.scaffolding.sophia.common.security.util;

import com.scaffolding.sophia.common.security.model.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;

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
        // return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //判断是否可以转换
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof LoginUser) {
            return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return null;
    }
}
