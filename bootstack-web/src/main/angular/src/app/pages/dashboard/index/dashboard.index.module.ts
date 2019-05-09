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
import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {TooltipModule} from 'ngx-bootstrap/tooltip';
import {BsDatepickerModule} from 'ngx-bootstrap/datepicker';
import {AngularEchartsModule} from 'ngx-echarts';
import {ModalModule} from 'ngx-bootstrap/modal';

import {DashboardIndexComponent} from "./dashboard.index.component";


const DASHBOARD_INDEX_ROUTE = [
    {path: '', component: DashboardIndexComponent}
];

@NgModule({
    declarations: [
        DashboardIndexComponent
    ],
    imports: [
        CommonModule,
        AngularEchartsModule,
        TooltipModule.forRoot(),
        BsDropdownModule.forRoot(),
        BsDatepickerModule.forRoot(),
        ModalModule.forRoot(),
        RouterModule.forChild(DASHBOARD_INDEX_ROUTE)
    ],
    providers: []
})

export class DashboardIndexModule {
}
