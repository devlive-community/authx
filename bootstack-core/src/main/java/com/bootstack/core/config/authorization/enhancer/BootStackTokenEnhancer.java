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
package com.bootstack.core.config.authorization.enhancer;

import com.bootstack.common.enums.SystemMessageEnums;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> BootStackTokenEnhancer </p>
 * <p> Description : BootStackTokenEnhancer </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-24 14:27 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Component(value = "bootStackTokenEnhancer")
public class BootStackTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("code", SystemMessageEnums.SYSTEM_SUCCESS.getCode());
        additionalInfo.put("message", SystemMessageEnums.SYSTEM_SUCCESS.getValue());
        additionalInfo.put("data", oAuth2AccessToken.getValue());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        // rest oauth2 other data
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setTokenType(null);
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setScope(null);
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setExpiration(null);
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setValue(null);
        return oAuth2AccessToken;
    }

}