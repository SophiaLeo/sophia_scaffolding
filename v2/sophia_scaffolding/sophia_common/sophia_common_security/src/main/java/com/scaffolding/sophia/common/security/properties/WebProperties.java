package com.scaffolding.sophia.common.security.properties;

import com.scaffolding.sophia.common.base.enums.LoginType;
import lombok.Getter;
import lombok.Setter;


/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.security.properties
 * @ClassName: WebProperties
 * @Description:
 * @Version: 1.0
 */
@Getter
@Setter
public class WebProperties {

    private String loginPage;
    // private String loginPage = GlobalsConstants.LOGIN_PAGE_URI;

    private LoginType loginType = LoginType.JSON;

    private String[] unInterceptUris = {};
}
