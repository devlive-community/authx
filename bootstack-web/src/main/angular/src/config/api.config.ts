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
export class ApiConfig {

    public static AUTHORIZATION_API = '/oauth/token';

    private static API_VERSION_V1 = '/api/v1/';

    /**
     * user api interface
     */
    public static readonly API_USER_REGISTER = ApiConfig.API_VERSION_V1 + 'user/register';
    public static readonly API_USER_INFO = ApiConfig.API_VERSION_V1 + 'user/info/';

    /**
     * system role interface
     */
    public static readonly API_SYSTEM_ROLE = ApiConfig.API_VERSION_V1 + 'system/role';

    /**
     * system menu type interface
     */
    public static readonly API_SYSTEM_MENU_TYPE = ApiConfig.API_VERSION_V1 + 'system/menu/type';

    /**
     * system menu interface
     */
    public static readonly API_SYSTEM_MENU = ApiConfig.API_VERSION_V1 + 'system/menu';

    /**
     * system settings interface
     */
    public static readonly API_SYSTEM_SETTINGS_INTERFACE = ApiConfig.API_VERSION_V1 + 'system/interface';

}
