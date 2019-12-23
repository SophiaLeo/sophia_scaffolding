// package com.scaffolding.sophia.zuul.config;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.annotation.Order;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.filter.CorsFilter;
//
// /**
//  * @author: LHL
//  * @ProjectName: sophia_scaffolding
//  * @Package: com.scaffolding.sophia.zuul.config
//  * @ClassName: CorsConfig
//  * @Description: 跨域配置
//  * @Version: 1.0
//  */
// @Configuration
// public class CorsConfig {
//
//     /**
//      * 跨域处理
//      */
//     @Bean
//     @Order(Integer.MAX_VALUE)
//     public CorsFilter corsFilter() {
//         final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//         final CorsConfiguration corsConfiguration = new CorsConfiguration();
//         corsConfiguration.setAllowCredentials(true);
//         corsConfiguration.addAllowedOrigin("*");
//         corsConfiguration.addAllowedHeader("*");
//         corsConfiguration.addAllowedMethod("*");
//         urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//         return new CorsFilter(urlBasedCorsConfigurationSource);
//     }
//
// }
