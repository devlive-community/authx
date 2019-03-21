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
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {Cookie} from 'ng2-cookies';

import {HttpUtils} from '../../app/shared/utils/http.util';
import {CookieUtils} from '../../app/shared/utils/cookie.util';

import {ApiConfig} from '../../config/api.config';
import {CommonConfig} from '../../config/common.config';

import {UserLoginParam} from '../../app/shared/param/user/user.login.param';
import {ToastyService} from 'ng2-toasty';
import {UserParam} from "../../app/shared/param/user/user.param";
import {Observable} from "rxjs";
import {CommonResponseModel} from "../../app/shared/model/common/response/response.model";
import {ResponseUtils} from "../../app/shared/utils/response.util";
import {BaseService} from "../base.service";
import {CommonPageModel} from "../../app/shared/model/common/response/page.model";

/**
 * user service
 */
@Injectable()
export class UserService implements BaseService {

    constructor(
        private http: Http,
        private options: RequestOptions,
        private router: Router,
        private toastyService: ToastyService) {
    }

    /**
     * login
     * @param param user login info
     */
    login(param: UserLoginParam) {
        Cookie.set(CommonConfig.AUTH_USER_NAME, param.username);
        const params = HttpUtils.getParams();
        params.append('username', param.username);
        params.append('password', param.password);
        params.append('grant_type', CommonConfig.AUTH_GRANT_TYPE);
        params.append('client_id', CommonConfig.AUTH_CLIENT_ID);
        const options = HttpUtils.getDefaultRequestOptionsByClient();
        options.params = params;
        return this.http.post(ApiConfig.AUTHORIZATION_API, param.toJson(), options)
            .map(res => res.json())
            .subscribe(
                data => {
                    this.saveToken(data);
                    return true;
                },
                error => {
                    CookieUtils.clearBy(CommonConfig.AUTH_USER_NAME);
                    this.toastyService.error('Login failed, please check your user name or password.');
                    return false;
                });
    }

    /**
     * register user
     * @param param user info
     */
    register(param: UserParam): Observable<CommonResponseModel> {
        const options = HttpUtils.getDefaultRequestOptions();
        return this.http.post(ApiConfig.API_USER_REGISTER, JSON.stringify(param), options)
            .map(ResponseUtils.extractData);
    }

    /**
     * logout
     */
    logout() {
        CookieUtils.clear();
    }

    /**
     * save token
     * @param token token
     */
    saveToken(token) {
        const expire = new Date();
        const time = Date.now() + ((3600 * 1000) * 1); // save token to cookie 1 hour
        expire.setTime(time);
        Cookie.set(CommonConfig.AUTH_TOKEN, token.data, expire);
        this.router.navigate(['/']);
    }

    /**
     * validate
     */
    checkCredentials() {
        if (!CookieUtils.get()) {
            this.router.navigate(['/user/login']);
        }
    }

    getInfo(primaryKey: Object): Observable<CommonResponseModel> {
        const options = HttpUtils.getDefaultRequestOptionsByTokenAndJSON();
        const path = ApiConfig.API_USER_INFO + primaryKey.toString();
        return this.http.get(path, options).map(ResponseUtils.extractData);
    }

    getList(page: CommonPageModel): Observable<CommonResponseModel> {
        return undefined;
    }

}
