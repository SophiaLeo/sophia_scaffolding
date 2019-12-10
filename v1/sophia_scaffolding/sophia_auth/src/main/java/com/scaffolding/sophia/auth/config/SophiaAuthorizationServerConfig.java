package com.scaffolding.sophia.auth.config;

import com.scaffolding.sophia.common.base.constants.GlobalsConstants;
import com.scaffolding.sophia.common.security.component.SophiaWebResponseExceptionTranslator;
import com.scaffolding.sophia.common.security.config.JwtTokenEnhancer;
import com.scaffolding.sophia.common.security.properties.OAuth2ClientProperties;
import com.scaffolding.sophia.common.security.properties.SophiaSecurityProperties;
import com.scaffolding.sophia.common.security.service.SophiaUserDetailService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import java.util.Arrays;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.auth.config
 * @ClassName: SophiaAuthorizationServerConfig
 * @Description: 认证服务
 * @Version: 1.0
 */
@Configuration
@EnableAuthorizationServer
public class SophiaAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private SophiaUserDetailService sophiaUserDetailService;
    @Autowired
    private SophiaSecurityProperties securityProperties;
    @Autowired
    private RedisConnectionFactory connectionFactory;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    /**
     * 配置客户端详情信息，客户端详情信息在这里进行初始化，通过数据库来存储调取详情信息
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
            for (OAuth2ClientProperties client : securityProperties.getOauth2().getClients()) {
                builder
                        .withClient(client.getClientId())
                        .secret(new BCryptPasswordEncoder().encode(client.getClientSecret()))
                        // .resourceIds("admin","auth")
                        //设置token的有效期，不设置默认12小时
                        .accessTokenValiditySeconds(client.getAccessTokenValidatySeconds())
                        //设置刷新token的有效期，不设置默认30天
                        .refreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds())
                        .redirectUris("http://www.baidu.com")
                        .authorizedGrantTypes("authorization_code","client_credentials", "refresh_token", "password")
                        .scopes("all", "read", "write")
                        .autoApprove(true);
            }
        }
    }

    /**
     * 配置授权服务器端点
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // 自定义jwt生成token方式
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
        //指定认证管理器
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(sophiaUserDetailService)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                //指定token存储位置
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter())
                // 自定义jwt生成token方式
                .tokenEnhancer(tokenEnhancerChain)
                // 配置TokenServices参数 如果需要jw的token而不是默认生成的uuid 那把他注释
                //.tokenServices(defaultTokenServices())
                .tokenServices(defaultTokenServices())
                .reuseRefreshTokens(false)
        // ;  //自定义异常处理
                .exceptionTranslator(new SophiaWebResponseExceptionTranslator());
    }


    /**
     * 注入自定义token生成方式（jwt）
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new JwtTokenEnhancer();
    }

    /**
     * 注意，自定义TokenServices的时候，需要设置@Primary，否则报错
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        // 这里如果设置为false则不能更新refresh_token，如果需要刷新token的功能需要设置成true
        tokenServices.setSupportRefreshToken(true);
        // 设置上次RefreshToken是否还可以使用 默认为true
        tokenServices.setReuseRefreshToken(false);
        // token有效期自定义设置，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 6);
        // refresh_token默认30天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 8);
        tokenServices.setTokenEnhancer(tokenEnhancer());
        return tokenServices;
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(passwordEncoder)
                //允许表单认证
                .allowFormAuthenticationForClients();
    }

    /**
     * 对Jwt签名时，增加一个密钥
     * JwtAccessTokenConverter：对Jwt来进行编码以及解码的类
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        //测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
        accessTokenConverter.setSigningKey(GlobalsConstants.OAUTH_SIGNING_KEY);
        return accessTokenConverter;
    }

    /**
     * token store
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(connectionFactory);
    }

}
