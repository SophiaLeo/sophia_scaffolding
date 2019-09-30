先创建数据库 sophia 并且设置字符集 utf8mb4

项目结构

--sophia_scaffolding

    --sophia_admin 用户服务集成资源配置

      --sophia_admin_api feign调用的一些接口

      --sophia_admin_biz  用户微服务

    --sophia_auth 认证微服务集成资源配置

    --sophia_common 公共包

      --sophia_common_base 公共基础

      --sophia_common_config 公共配置包

      --sophia_common_feign 公共feign配置处理包

      --sophia_common_security 公共的security包 主要是资源服务相关配置

    --sophia_eureka eureka server注册中心

    --sophia_zuul 统一网关 集成oauth2
  
  
  项目启动
  
  1.eureka
  
  2.auth
  
  3.admin_biz
  
  4.zuul

