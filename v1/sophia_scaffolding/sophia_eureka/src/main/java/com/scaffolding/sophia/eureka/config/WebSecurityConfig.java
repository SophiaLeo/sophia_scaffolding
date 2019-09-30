package com.scaffolding.sophia.eureka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffplding.sophia.eureka.config
 * @ClassName: WebSecurityConfig
 * @Description: security配置
 * @Version: 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @Description: 高版本的丢弃了
     * security:
     *   basic:
     *     enabled: true 配置，应该使用以下方式开启
     * @Param: [http]
     * @Return: void
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Configure HttpSecurity as needed (e.g. enable http basic).
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        //注意：为了可以使用 http://${user}:${password}@${host}:${port}/eureka/ 这种方式登录,所以必须是httpBasic,
        // 如果是form方式,不能使用url格式登录
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .anyRequest()
                .authenticated().and().httpBasic();
    }

}