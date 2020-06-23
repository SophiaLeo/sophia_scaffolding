package com.scaffolding.sophia.common.log.event;

import com.scaffolding.sophia.admin.api.entity.bo.ApiLogger;
import org.springframework.context.ApplicationEvent;

/**
 * @author LHL
 * 系统日志事件
 */
public class SysLogEvent extends ApplicationEvent {

	public SysLogEvent(ApiLogger apiLogger) {
		super(apiLogger);
	}
}