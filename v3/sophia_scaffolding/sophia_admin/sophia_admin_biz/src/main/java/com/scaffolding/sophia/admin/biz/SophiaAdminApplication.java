package com.scaffolding.sophia.admin.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@MapperScan({"com.scaffolding.sophia.admin.biz.mapper"})
@EnableFeignClients(basePackages = "com.scaffolding.sophia.admin.api.feign.client")
@ComponentScan(basePackages = {"com.scaffolding.sophia"})
// @EnableSwagger2Doc
public class SophiaAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SophiaAdminApplication.class, args);
    }

    @GetMapping("/app1")
    public Object user(Authentication authentication) {
        return authentication.getPrincipal();
    }

    @GetMapping("/app2")
    public Object user2() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
