package com.scaffolding.sophia.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scaffolding.sophia.common.security.component.SophiaAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.security.exception
 * @ClassName:  ServerErrorException
 * @Description:
 * @Version: 1.0
 */
@JsonSerialize(using = SophiaAuth2ExceptionSerializer.class)
public class ServerErrorException extends SophiaAuth2Exception {

	public ServerErrorException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "server_error";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

}
