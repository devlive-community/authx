/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.devlive.authx.server.config.authorization.exception;

import org.devlive.authx.common.date.DateUtils;
import org.devlive.authx.common.enums.SystemMessageEnums;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * <p> BootStackOauthExceptionSerializer </p>
 * <p> Description : BootStackOauthExceptionSerializer </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-24 14:30 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public class BootStackOauthExceptionSerializer extends StdSerializer<BootStackOauthException> {

    public BootStackOauthExceptionSerializer() {
        super(BootStackOauthException.class);
    }

    @Override
    public void serialize(BootStackOauthException value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("code", String.valueOf(SystemMessageEnums.SYSTEM_BAD_CREDENTIALS.getCode()));
        generator.writeStringField("message", SystemMessageEnums.SYSTEM_BAD_CREDENTIALS.getValue());
        generator.writeStringField("data", DateUtils.formatYmdhms());
        generator.writeEndObject();
    }

}