package com.scaffolding.sophia.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SophiaEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SophiaEurekaApplication.class, args);
    }

}
