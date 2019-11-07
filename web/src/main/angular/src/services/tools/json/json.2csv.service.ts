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
import {Http} from '@angular/http';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {Observable} from "rxjs";
import {BaseService} from "../../base.service";
import {CommonResponseModel} from "../../../app/shared/model/common/response/response.model";
import {HttpUtils} from "../../../app/shared/utils/http.util";
import {ApiConfig} from "../../../config/api.config";
import {ResponseUtils} from "../../../app/shared/utils/response.util";
import {CommonPageModel} from "../../../app/shared/model/common/response/page.model";

/**
 * json 2 csv service
 */
@Injectable()
export class Json2csvService implements BaseService {

    constructor(private http: Http) {
    }

    getInfo(primaryKey: Object): Observable<CommonResponseModel> {
        return undefined;
    }

    getList(page: CommonPageModel): Observable<CommonResponseModel> {
        return undefined;
    }

    update(param: Object): Observable<CommonResponseModel> {
        return undefined;
    }

    register(param: Object): Observable<CommonResponseModel> {
        return undefined;
    }

    /**
     * 转换数据
     * @param body 源数据
     */
    convertToCsv(body: string): Observable<CommonResponseModel> {
        const options = HttpUtils.getDefaultRequestOptionsByTokenAndJSON();
        return this.http.post(ApiConfig.API_TOOLS_JSON_2CSV, body, options)
            .map(ResponseUtils.extractData);
    }

}
