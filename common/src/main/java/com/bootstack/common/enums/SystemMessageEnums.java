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
package org.devlive.authx.common.enums;

/**
 * <p> SystemMessageEnums </p>
 * <p> Description : SystemMessageEnums </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-24 14:12 </p>
 * <p> Author Eamil: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public enum SystemMessageEnums {

    SYSTEM_SUCCESS(2000, "query success"),
    SYSTEM_UNAUTHORIZED(4000, "Invalid access permissions, please contact administrator to assign permissions"),
    SYSTEM_ERROR_TOKEN(4001, "this is a not active token"),
    SYSTEM_BAD_CREDENTIALS(4002, "bad user or password"),
    SYSTEM_PARAMS_VALIDATE_ERROR(4003, "param validation error, please check"),
    SYSTEM_BODY_MUST_NULL(4004, "required request body is missing"),
    SYSTEM_MEDIA_TYPE_NOT_SUPPORT(4005, "media type not supported or media type must not null"),
    SYSTEM_METHOD_CONVERT_NOT_SUPPORT(4006, "failed to convert id type"),
    SYSTEM_PARAM_MUST_NULL(4007, "required request param is missing"),
    SYSTEM_PARAM_PATH_MUST_NULL(4008, "required request path param is missing"),
    SYSTEM_USER_EXISTS(4009, "user exists, please replace user name"),
    SYSTEM_METHOD_NOT_SUPPORT(4010, "request method not supported"),
    SYSTEM_PARAMS_NOT_NULL(4011, "params must not null"),

    SYSTEM_JSON_ERROR(4100, "source not a valid json data")
    ;

    private Integer code;
    private String value;

    SystemMessageEnums(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Integer getCode() {
        return code;
    }

}
