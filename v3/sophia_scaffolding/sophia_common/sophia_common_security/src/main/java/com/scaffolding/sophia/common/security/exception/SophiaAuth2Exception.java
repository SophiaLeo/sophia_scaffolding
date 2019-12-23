package com.scaffolding.sophia.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scaffolding.sophia.common.security.component.SophiaAuth2ExceptionSerializer;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.security.exception
 * @ClassName: SophiaAuth2Exception
 * @Description: 自定义OAuth2Exception
 * @Version: 1.0
 */
@JsonSerialize(using = SophiaAuth2ExceptionSerializer.class)
public class SophiaAuth2Exception extends OAuth2Exception {
	@Getter
	private String errorCode;

	public SophiaAuth2Exception(String msg) {
		super(msg);
	}

	public SophiaAuth2Exception(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}
}
