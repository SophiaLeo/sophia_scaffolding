package com.scaffolding.sophia.common.security.properties;

import com.scaffolding.sophia.common.base.constants.GlobalsConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.security.properties
 * @ClassName: SophiaSecurityProperties
 * @Description:
 * @Version: 1.0
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = GlobalsConstants.SOPHIA_OAUTH_PREFIX)
public class SophiaSecurityProperties {

    WebProperties web = new WebProperties();

    OAuth2Properties oauth2 = new OAuth2Properties();

}
