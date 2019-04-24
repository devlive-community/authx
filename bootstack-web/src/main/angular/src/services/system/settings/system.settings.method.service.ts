/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import {Injectable} from "@angular/core";
import {Http, RequestOptions} from "@angular/http";
import {Router} from "@angular/router";
import {ToastyService} from "ng2-toasty";
import {Observable} from "rxjs";
import {BaseService} from "../../base.service";
import {CommonResponseModel} from "../../../app/shared/model/common/response/response.model";
import {CommonPageModel} from "../../../app/shared/model/common/response/page.model";
import {HttpUtils} from "../../../app/shared/utils/http.util";
import {ApiConfig} from "../../../config/api.config";
import {ResponseUtils} from "../../../app/shared/utils/response.util";
import {SystemSettingsMethodParam} from "../../../app/shared/param/system/settings/method/system.settings.method.param";

/**
 * System Settings Method Service
 */
@Injectable()
export class SystemSettingsMethodService implements BaseService {

    constructor(
        private http: Http,
        private options: RequestOptions,
        private router: Router,
        private toastyService: ToastyService) {
    }

    getInfo(primaryKey: Object): Observable<CommonResponseModel> {
        return undefined;
    }

    getList(page: CommonPageModel): Observable<CommonResponseModel> {
        const options = HttpUtils.getDefaultRequestOptionsByTokenAndJSON();
        const params = HttpUtils.getParams();
        params.append('page', page.number.toString());
        params.append('size', page.size.toString());
        options.params = params;
        return this.http.get(ApiConfig.API_SYSTEM_SETTINGS_METHOD, options).map(ResponseUtils.extractData);
    }

    register(param: SystemSettingsMethodParam): Observable<CommonResponseModel> {
        const options = HttpUtils.getDefaultRequestOptionsByTokenAndJSON();
        return this.http.post(ApiConfig.API_SYSTEM_SETTINGS_METHOD, JSON.stringify(param), options)
            .map(ResponseUtils.extractData);
    }

    update(param: SystemSettingsMethodParam): Observable<CommonResponseModel> {
        const options = HttpUtils.getDefaultRequestOptionsByTokenAndJSON();
        return this.http.put(ApiConfig.API_SYSTEM_SETTINGS_METHOD, JSON.stringify(param), options)
            .map(ResponseUtils.extractData);
    }

}
