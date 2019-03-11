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
import {Injectable} from '@angular/core';
import {Http, RequestOptions} from '@angular/http';
import {Router} from '@angular/router';
import {CookieUtils} from '../app/shared/utils/cookie.utils';

/**
 * user service
 */
@Injectable()
export class UserService {

  constructor(
    private http: Http,
    private options: RequestOptions,
    private router: Router) {
  }

  /**
   * user login
   */
  login() {
    // Cookie.set(CommonConfig.AUTH_USER_NAME, param.username);
    // const params = HttpUtils.getParams();
    // params.append('username', param.username);
    // params.append('password', param.password);
    // params.append('grant_type', CommonConfig.AUTH_GRANT_TYPE);
    // params.append('client_id', CommonConfig.AUTH_CLIENT_ID);
    // const options = HttpUtils.getDefaultRequestOptionsByClient();
    // options.params = params;
    return false;
  }

  /**
   * check login status
   */
  checkCredentials() {
    if (!CookieUtils.get()) {
      this.router.navigate(['/user/login']);
    }
  }

}
