package com.scaffolding.sophia.auth.config;

import com.scaffolding.sophia.common.security.config.SophiaResourceServerConfig;
import com.scaffolding.sophia.common.security.properties.SophiaSecurityProperties;
import com.scaffolding.sophia.common.security.service.SophiaUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.auth.config
 * @ClassName: SophiaWebSecurityConfig
 * @Description: web security 访问安全配置
 * @Version: 1.0
 */
@EnableWebSecurity
@Configuration
@AutoConfigureBefore({SophiaResourceServerConfig.class, SophiaAuthorizationServerConfig.class})
public class SophiaWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SophiaUserDetailService sophiaUserDetailService;
    @Autowired
   private SophiaSecurityProperties securityProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers(securityProperties.getWeb().getUnInterceptUris())
                .permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf()
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sophiaUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
