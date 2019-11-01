package com.scaffolding.sophia.admin.biz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.csrf().disable();
        //对配置的url放行 不进行验证
        http.requestMatchers()
                .anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/api/login","/**/api/**","/actuator/**","/v2/api-docs","/swagger-ui.html","/webjars/**","/swagger-resources/**")
                .permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf()
                .disable();
    }

}
