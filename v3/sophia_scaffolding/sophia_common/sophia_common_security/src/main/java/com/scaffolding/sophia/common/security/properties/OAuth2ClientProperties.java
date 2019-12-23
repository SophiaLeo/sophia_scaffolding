package com.scaffolding.sophia.common.security.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.security.properties
 * @ClassName: OAuth2ClientProperties
 * @Description:
 * @Version: 1.0
 */
@Getter
@Setter
public class OAuth2ClientProperties {

    private String clientId;

    private String clientSecret;

    // private String resourceIds;

    private int accessTokenValidatySeconds;

    private int refreshTokenValiditySeconds;

}
