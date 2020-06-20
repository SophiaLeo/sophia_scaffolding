package com.scaffolding.sophia.common.log;

import com.scaffolding.sophia.common.log.aspect.ApiLogAspect;
import com.scaffolding.sophia.common.log.event.SysLogListener;
import feign.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author LHL
 * 日志自动配置
 */
@EnableAsync
@Configuration
@ConditionalOnWebApplication
@EnableFeignClients({"com.scaffolding.sophia.admin.api.feign"})
public class LogAutoConfiguration {

	@Bean
	public SysLogListener sysLogListener() {
		return new SysLogListener();
	}

	@Bean
	public ApiLogAspect apiLogAspect(ApplicationEventPublisher publisher) {
		return new ApiLogAspect(publisher);
	}

	@Bean
	public Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

}