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
export class SystemSettingsTableRowParam {

    public id: number;
    public title: string;
    public properties: string; // 对应数据的字段
    public checked: boolean = false; // 选中状态
    public type: number = 0; // 字段类型,后期支持排序等功能
    public sorted: number = 0; // 排列顺序
    public active: boolean = true;
    public menus: any; // 可使用的菜单列表标志

    constructor() {
    }

}
