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
import {Headers, QueryEncoder, RequestOptions, URLSearchParams} from '@angular/http';
// @ts-ignore
import {CommonConfig} from '../../../config/common.config';

import {CookieUtils} from '../utils/cookie.utils';
import {CommonPageModel} from "../model/response/common.page.model";

/**
 * common http tool
 *
 * @author qianmoQ
 */
export class HttpUtils {

  /**
   * The default parameter header USES JSON data transfer
   */
  public static getDefaultRequestOptions() {
    const headers = new Headers({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
    const options = new RequestOptions({headers: headers});
    return options;
  }

  public static getDefaultRequestOptionsByClient() {
    const headers = new Headers({
      'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Basic ' + btoa(CommonConfig.AUTH_CLIENT_ID + ':' + CommonConfig.AUTH_CLIENT_SECRET)
    });
    const options = new RequestOptions({headers: headers});
    return options;
  }

  public static getDefaultRequestOptionsByToken() {
    const headers = new Headers({
      'Authorization': 'Bearer ' + CookieUtils.get()
    });
    const options = new RequestOptions({headers: headers});
    return options;
  }

  public static getDefaultRequestOptionsByTokenAndJSON() {
    const headers = new Headers({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + CookieUtils.get()
    });
    const options = new RequestOptions({headers: headers});
    return options;
  }

  /**
   * find by page
   * @param page page no
   * @param size page size
   */
  public static getPaginationParams(page: number, size: number): URLSearchParams {
    const params = new URLSearchParams();
    params.append('page', page.toString());
    params.append('size', size.toString());
    return params;
  }

  /**
   * find by page
   * @param page page no
   * @param size page size
   */
  public static getPaginationParamsByModel(page: CommonPageModel): URLSearchParams {
    const params = new URLSearchParams();
    params.append('page', page.number.toString());
    params.append('size', page.size.toString());
    return params;
  }

  /**
   * query param
   */
  public static getParams(): URLSearchParams {
    const params = new URLSearchParams();
    return params;
  }

}

class UrlQueryEncoder extends QueryEncoder {
  encodeKey(k: string): string {
    return super.encodeKey(k).replace(/\+/gi, '%2B');
  }

  encodeValue(v: string): string {
    return super.encodeValue(v).replace(/\+/gi, '%2B');
  }
}

export const URL_QUERY_ENCODER = new UrlQueryEncoder();
