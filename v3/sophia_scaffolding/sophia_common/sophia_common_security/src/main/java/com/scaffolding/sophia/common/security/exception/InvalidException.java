package com.scaffolding.sophia.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scaffolding.sophia.common.security.component.SophiaAuth2ExceptionSerializer;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.security.exception
 * @ClassName: InvalidException
 * @Description:
 * @Version: 1.0
 */
@JsonSerialize(using = SophiaAuth2ExceptionSerializer.class)
public class InvalidException extends SophiaAuth2Exception {

	public InvalidException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "invalid_exception";
	}

	@Override
	public int getHttpErrorCode() {
		return 426;
	}

}
