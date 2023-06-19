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
    checkCredentials() {
        if (!CookieUtils.get()) {
            this.router.navigate(['/user/login']);
        }
    }

    getList(page: CommonPageModel): Observable<CommonResponseModel> {
        const options = HttpUtils.getDefaultRequestOptionsByTokenAndJSON();
        const params = HttpUtils.getParams();
        params.append('page', page.number.toString());
        params.append('size', page.size.toString());
        options.params = params;
        return this.http.get(ApiConfig.API_USER, options).map(ResponseUtils.extractData);
    }

    update(param: Object): Observable<CommonResponseModel> {
        const options = HttpUtils.getDefaultRequestOptionsByTokenAndJSON();
        return this.http.put(ApiConfig.API_USER, JSON.stringify(param), options).map(ResponseUtils.extractData);
    }
}
