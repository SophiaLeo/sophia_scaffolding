package com.scaffolding.sophia.common.security.service;

import com.alibaba.fastjson.JSON;
import com.scaffolding.sophia.admin.api.entity.authority.OauthClientDetails;
import com.scaffolding.sophia.admin.api.feign.client.AuthorityClient;
import com.scaffolding.sophia.common.base.enums.SophiaHttpStatus;
import com.scaffolding.sophia.common.base.exception.CommonException;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.security.service
 * @ClassName: SophiaClientDetailsService
 * @Description: 客户端详情信息，客户端详情信息在这里进行初始化，通过数据库来存储调取详情信息
 * 用户登录时（即携带参数请求/oauth/token接口）会调用这两个service。
 * 1、SophiaClientDetailsService是根据client_id查出来的信息验证用户登录时携带的参数（即客户端详情表信息）是否正确。并且设置能访问的资源id集合。
 * 2、SophiaUserDetailService是根据用户名去查用户密码，用户所拥有的角色等信息，然后丢给security去验证用户登录时的用户名和密码是否正确。
 * 以上都正确则返回token信息，并把token信息存到了redis里,然后就可以根据拿到的token去请求被security管理起来的接口地址。
 * @Version: 1.0
 */
@Service
public class SophiaClientDetailsService implements ClientDetailsService {

    private  static  final Logger log = LoggerFactory.getLogger(SophiaClientDetailsService.class);

    @Autowired
    private AuthorityClient authorityClient;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ApiResponse apiResponse = authorityClient.getOauthClientDetailsByClientId(clientId);
        OauthClientDetails model  = JSON.parseObject(JSON.toJSONString( apiResponse.getData(), true),OauthClientDetails.class);
        if (model == null) {
            throw new CommonException(SophiaHttpStatus.CLIENT_ERROR);
        }
        BaseClientDetails clientDetails = new BaseClientDetails();
        //客户端(client)id
        clientDetails.setClientId(model.getClientId());
        //客户端所能访问的资源id集合
        if (StringUtils.isNotEmpty(model.getResourceIds())) {
            clientDetails.setResourceIds(Arrays.asList(model.getResourceIds().split(",")));
        }
        //客户端(client)的访问密匙
        clientDetails.setClientSecret(new BCryptPasswordEncoder().encode(model.getClientSecret()));
        //客户端支持的grant_type授权类型
        clientDetails.setAuthorizedGrantTypes(Arrays.asList(model.getAuthorizedGrantTypes().split(",")));
        //客户端申请的权限范围
        clientDetails.setScope(Arrays.asList(model.getScope().split(",")));
        Integer accessTokenValidity = model.getAccessTokenValidity();
        if (accessTokenValidity != null && accessTokenValidity > 0) {
            //设置token的有效期，不设置默认12小时
            clientDetails.setAccessTokenValiditySeconds(accessTokenValidity);
        }
        Integer refreshTokenValidity = model.getRefreshTokenValidity();
        if (refreshTokenValidity != null && refreshTokenValidity > 0) {
            //设置刷新token的有效期，不设置默认30天
            clientDetails.setRefreshTokenValiditySeconds(refreshTokenValidity);
        }
        clientDetails.isAutoApprove(model.getAutoapprove());
        log.debug("clientId是：" + clientId);
        return clientDetails;
    }
}
