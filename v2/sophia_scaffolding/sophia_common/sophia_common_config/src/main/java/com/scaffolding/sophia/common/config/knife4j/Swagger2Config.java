package com.scaffolding.sophia.common.config.knife4j;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.config.knife4j
 * @ClassName: Swagger2Config
 * @Description: knife4j时使用 只用swagger时将类注释掉
 * @Version: 1.0
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
@EnableKnife4j
@ConfigurationProperties(prefix="swagger")
@Getter
@Setter
public class Swagger2Config {

    private String title;

    private String contact;

    private String version;

    private String license;

    private String licenseUrl;

    private String termsOfServiceUrl;

    private String description;

    private String basePackage;

    /**
     * 创建RestApi 并包扫描controller
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                // .groupName("2.X版本")
                .select()
                // // 对所有api进行监控
                // .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                //不显示错误的接口地址
                .paths(PathSelectors.any())
                //错误路径不监控
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                // .build();
                .build()
                .securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.<SecurityScheme>newArrayList(apiKey()));

    }

    /**
     * 创建Swagger页面 信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().
                title(title).
                contact(contact).
                version(version).
                license(license).
                licenseUrl(licenseUrl).
                termsOfServiceUrl(termsOfServiceUrl).
                description(description)
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }


    private ApiKey apiKey() {
        return new ApiKey("BearerToken", "Authorization", "header");
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("BearerToken", authorizationScopes));
    }
}
