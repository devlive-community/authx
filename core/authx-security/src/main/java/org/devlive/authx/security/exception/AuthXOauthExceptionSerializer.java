package org.devlive.authx.security.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.devlive.authx.common.date.DateUtils;
import org.devlive.authx.common.enums.SystemMessageEnums;

import java.io.IOException;

public class AuthXOauthExceptionSerializer extends StdSerializer<AuthXOauthException> {

    public AuthXOauthExceptionSerializer() {
        super(AuthXOauthException.class);
    }

    @Override
    public void serialize(AuthXOauthException value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("code", String.valueOf(SystemMessageEnums.SYSTEM_BAD_CREDENTIALS.getCode()));
        generator.writeStringField("message", SystemMessageEnums.SYSTEM_BAD_CREDENTIALS.getValue());
        generator.writeStringField("data", DateUtils.formatYmdhms());
        generator.writeEndObject();
    }
}