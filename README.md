# sophia_scaffolding

#### 介绍
SpringCloud+OAuth2+Spring Security+Redis+Jwt+zuul(gateway)+(Nacos)实现的微服务统一认证授权 后端脚手架

博客 https://blog.csdn.net/Amor_Leo 

blog: 

SpringCloud+OAuth2+Spring Security+Redis+Jwt+zuul(gateway)+Eureka 实现的微服务统一认证授权

https://blog.csdn.net/Amor_Leo/article/details/101751690

gateway和swagger 整合 

https://blog.csdn.net/Amor_Leo/article/details/102853186

Knife4j  和 Gateway 整合
https://blog.csdn.net/Amor_Leo/article/details/106470845

Nacos搭建

https://blog.csdn.net/Amor_Leo/article/details/103496056

前端只写了登录
借用了 elementUI-admin

#### 使用框架软件
+ IDEA
+ JDK 8+
+ MySQL 5.7+
+ Redis
+ SpringBoot 2.2.2(v3)
+ SpringCloud Hoxton.RELEASE(v3)       
+ jwt
+ oauth2
+ nacos(v3)

#### 软件架构
```
--sophia_admin 用户服务集成资源配置

  --sophia_admin_api feign调用的一些接口

  --sophia_admin_biz  用户微服务

--sophia_auth 认证微服务集成资源配置

--sophia_common 公共包

  --sophia_common_base 公共基础

  --sophia_common_config 公共配置包

  --sophia_common_feign 公共feign配置处理包

  --sophia_common_security 公共的security包 主要是资源服务相关配置

  --sophia_common_util 公共工具类

  --sophia_common_log 公共日志配置
 
--sophia_eureka eureka server注册中心

--sophia_zuul 统一网关 

--sophia_gateway 统一网关 
```


#### 安装教程

1.  安装redis
2.  创建sophia数据库 utf8mb4
3.  如果使用v3 请搭建nacos

#### 接口
![](https://img-blog.csdnimg.cn/20191223161738114.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0Ftb3JfTGVv,size_16,color_FFFFFF,t_70)
