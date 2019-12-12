package com.scaffolding.sophia.common.security.config;

import com.scaffolding.sophia.common.base.constants.GlobalsConstants;
import com.scaffolding.sophia.common.security.component.MyAccessDeniedHandler;
import com.scaffolding.sophia.common.security.component.MyAuthExceptionEntryPoint;
import com.scaffolding.sophia.common.security.properties.FilterIgnoreProperties;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.security.config
 * @ClassName: SophiaResourceServerConfig
 * @Description: 资源服务 资源访问权限配置: 给接口地址让security管理起来，如哪些不需要授权能访问;哪些需要登录授权后能访问，哪些需要用户拥有这些角色才能访问。
 * 优先级低于AuthorizationServerConfigurerAdapter
 * @Version: 1.0
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan({"com.scaffolding.sophia.common.security"})
public class SophiaResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private FilterIgnoreProperties ignorePropertiesConfig;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 对Jwt签名时，增加一个密钥
     * JwtAccessTokenConverter：对Jwt来进行编码以及解码的类
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //对称加密方式 测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
        jwtAccessTokenConverter.setSigningKey(GlobalsConstants.OAUTH_SIGNING_KEY);
        return jwtAccessTokenConverter;
        //非对称加密方式(jks文件可能过期，jks文件需要Java keytool工具生成)
//        Resource resource = new ClassPathResource("publicKey.txt");
//        String publicKey ;
//        try {
//            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//          设置公钥
//        jwtAccessTokenConverter.setVerifierKey(publicKey);
    }

    /**
     * token store
     */
    @Bean
    public TokenStore tokenStore() {
//        return new InMemoryTokenStore();
        return new RedisTokenStore(redisConnectionFactory);
//        return new JwtTokenStore(jwtAccessTokenConverter());
//        return new JdbcTokenStore(dataSource);
    }

    @Override
    @SneakyThrows
    public void configure(HttpSecurity httpSecurity) {
        //允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        httpSecurity.headers().frameOptions().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>
                .ExpressionInterceptUrlRegistry registry = httpSecurity
                .authorizeRequests();
        //对配置的url放行 不进行验证
        ignorePropertiesConfig.getUrls()
                .forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest().authenticated()
                .and().csrf().disable();
    }


    @Override
    @CrossOrigin
    public void configure(ResourceServerSecurityConfigurer resources) {
        // String resourceIds = publicMapper.getResourceIdsByClientId(clientId);
        // //设置客户端所能访问的资源id集合(默认取第一个是本服务的资源)
        // resources.resourceId(resourceIds.split(",")[0]).stateless(true);
        // resources.resourceId("admin").stateless(true);
        resources
                .tokenStore(tokenStore())
                //自定义Token异常信息,用于token校验失败返回信息
                .authenticationEntryPoint(new MyAuthExceptionEntryPoint())
                //授权异常处理
                .accessDeniedHandler(new MyAccessDeniedHandler());
    }

}
