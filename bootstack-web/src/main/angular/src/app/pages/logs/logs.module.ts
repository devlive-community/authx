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
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import {TooltipModule} from 'ngx-bootstrap/tooltip';
import {CustomFormsModule} from 'ng2-validation';
import {ToastyModule} from 'ng2-toasty';
import {BusyModule} from 'angular2-busy';
import {TranslateModule} from '@ngx-translate/core';

import {ModalModule, PaginationModule} from "ngx-bootstrap";
import {LogsComponent} from "./logs.component";
import {SystemLogService} from "../../../services/system/system.log.service";

const LOGS_ROUTES: Routes = [
    {path: '', component: LogsComponent}
];

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        CustomFormsModule,
        BusyModule,
        TranslateModule,
        TooltipModule.forRoot(),
        ToastyModule.forRoot(),
        PaginationModule.forRoot(),
        ModalModule.forRoot(),
        RouterModule.forChild(LOGS_ROUTES)
    ],
    exports: [],
    declarations: [
        LogsComponent
    ],
    providers: [
        SystemLogService
    ],
})
export class LogsModule {
}
