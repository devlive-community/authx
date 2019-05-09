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
import { Serializable } from '../../serialization/serialization.model';

/**
 * page
 *
 * @author qianmoQ
 */
export class CommonPageModel implements Serializable<CommonPageModel> {

    public totalPages: number;
    public totalElements: number;
    public last: boolean;
    public size = 10;
    public number = 1;
    public first: number;
    public numberOfElements: number;
    public type: number;

    public deserialize(input) {
        return this;
    }

    // tslint:disable-next-line:member-ordering
    public static getPage(json) {
        const page: CommonPageModel = new CommonPageModel();
        page.totalPages = json.totalPages;
        page.totalElements = json.totalElements;
        page.last = json.last;
        page.size = json.size;
        page.number = json.number;
        page.first = json.first;
        page.numberOfElements = json.numberOfElements;
        return page;
    }

}
