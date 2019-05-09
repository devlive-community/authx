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
import { Cookie } from 'ng2-cookies';

import { CommonConfig } from '../../../config/common.config';

export class CookieUtils {

    /**
     * Get the authorization key
     */
    public static get() {
        return Cookie.get(CommonConfig.AUTH_TOKEN);
    }
    public static getBy(key: string) {
        return Cookie.get(key);
    }

    public static getUserName() {
        return Cookie.get(CommonConfig.AUTH_USER_NAME);
    }

    public static setBy(key: string, value: string) {
        const expire = new Date();
        const time = Date.now() + ((3600 * 1000) * 1); // Save the cookie for 1 hour
        expire.setTime(time);
        return Cookie.set(key, value, expire);
    }

    public static getUser() {
        const data = Cookie.get(CommonConfig.AUTH_USER_INFO);
        if (data.length > 0) {
            return JSON.parse(data);
        }
    }

    public static clear() {
        Cookie.deleteAll();
    }

    public static clearBy(key: string) {
        Cookie.delete(key);
    }

}
