package com.scaffolding.sophia.common.log.event;

import com.scaffolding.sophia.admin.api.entity.bo.ApiLogger;
import com.scaffolding.sophia.admin.api.feign.client.ApiLoggerClient;
import com.scaffolding.sophia.common.base.constants.GlobalsConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * @author LHL
 * 异步监听日志事件
 */
@Slf4j
public class SysLogListener {

	@Autowired
	private ApiLoggerClient apiLoggerClient;

	@Async
	@EventListener(SysLogEvent.class)
	public void saveSysLog(SysLogEvent event) {
		ApiLogger apiLogger = (ApiLogger) event.getSource();
		apiLoggerClient.saveLog(apiLogger, GlobalsConstants.FROM_IN);
	}
}
