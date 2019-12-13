package com.scaffolding.sophia.admin.api.entity.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.entity.authority
 * @ClassName: OauthClientDetails
 * @Description:
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName("sys_oauth_client_details")
@ApiModel(value = "OauthClientDetails",description = "Oauht2客户端详情设置")
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String autoapprove;

    private String additionalInformation;

}
