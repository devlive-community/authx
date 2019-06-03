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
import {BaseService} from "../base.service";
import {Http} from "@angular/http";
import {Observable} from "rxjs";
import {CommonResponseModel} from "../../app/shared/model/common/response/response.model";
import {CommonPageModel} from "../../app/shared/model/common/response/page.model";
import {HttpUtils} from "../../app/shared/utils/http.util";
import {ApiConfig} from "../../config/api.config";
import {ResponseUtils} from "../../app/shared/utils/response.util";
import {SystemSettingsTableRowParam} from "../../app/shared/param/system/settings/system.settings.table.row.param";

/**
 * 表格(头)后台查询服务
 */
@Injectable()
export class TableRowService implements BaseService {

    constructor(private http: Http) {
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
        return this.http.get(ApiConfig.API_TABLE_ROW, options).map(ResponseUtils.extractData);
    }

    register(param: SystemSettingsTableRowParam): Observable<CommonResponseModel> {
        const options = HttpUtils.getDefaultRequestOptionsByTokenAndJSON();
        return this.http.post(ApiConfig.API_TABLE_ROW, JSON.stringify(param), options)
            .map(ResponseUtils.extractData);
    }

    update(param: SystemSettingsTableRowParam): Observable<CommonResponseModel> {
        const options = HttpUtils.getDefaultRequestOptionsByTokenAndJSON();
        return this.http.put(ApiConfig.API_TABLE_ROW, JSON.stringify(param), options)
            .map(ResponseUtils.extractData);
    }

    /**
     * 根据菜单获取表头信息
     * @param menu 菜单信息
     * @param page 分页信息
     */
    getListByMenu(menu: string, page: CommonPageModel): Observable<CommonResponseModel> {
        const options = HttpUtils.getDefaultRequestOptionsByTokenAndJSON();
        const params = HttpUtils.getParams();
        params.append('page', page.number.toString());
        params.append('size', page.size.toString());
        options.params = params;
        return this.http.get(ApiConfig.API_TABLE_ROW + '/' + menu, options).map(ResponseUtils.extractData);
    }

}
