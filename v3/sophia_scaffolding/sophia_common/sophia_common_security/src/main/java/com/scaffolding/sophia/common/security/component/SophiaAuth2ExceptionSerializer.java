package com.scaffolding.sophia.common.security.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.scaffolding.sophia.common.base.constants.GlobalsConstants;
import com.scaffolding.sophia.common.security.exception.SophiaAuth2Exception;
import lombok.SneakyThrows;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.security.component
 * @ClassName: SophiaAuth2ExceptionSerializer
 * @Description: OAuth2 异常格式化
 * @Version: 1.0
 */
public class SophiaAuth2ExceptionSerializer extends StdSerializer<SophiaAuth2Exception> {


	public SophiaAuth2ExceptionSerializer() {
		super(SophiaAuth2Exception.class);
	}

	@Override
	@SneakyThrows
	public void serialize(SophiaAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
		gen.writeStartObject();
		gen.writeObjectField("code", GlobalsConstants.FAIL);
		gen.writeStringField("msg", value.getMessage());
		gen.writeStringField("data", value.getErrorCode());
		gen.writeEndObject();
	}
}
