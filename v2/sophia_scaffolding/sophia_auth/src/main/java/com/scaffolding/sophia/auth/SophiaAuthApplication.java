package com.scaffolding.sophia.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.scaffolding.sophia.admin.api.feign.client")
@ComponentScan(basePackages = {"com.scaffolding.sophia"})
//@EnableSwagger2Doc //已改成knife4j
public class SophiaAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SophiaAuthApplication.class, args);
    }

}
