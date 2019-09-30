package com.scaffolding.sophia.admin.biz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.config
 * @ClassName: SecurityConfig
 * @Version: 1.0
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }

    // @Override
    // protected void configure(HttpSecurity http) {
    //     try {
    //         //配置登录不需要验证
    //         http.authorizeRequests()
    //                 .anyRequest()
    //                 .permitAll()
    //                 .and()
    //                 .logout()
    //                 .permitAll();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
}
