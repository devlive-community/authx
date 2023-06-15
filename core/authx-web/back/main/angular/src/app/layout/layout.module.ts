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
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';

import {NgModule} from '@angular/core';
import {LayoutRouting} from './layout.routing';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {ProgressbarModule} from 'ngx-bootstrap/progressbar';
import {ButtonsModule} from 'ngx-bootstrap';
import {PerfectScrollbarConfigInterface, PerfectScrollbarModule} from 'ngx-perfect-scrollbar';
import {TabsModule} from 'ngx-bootstrap/tabs';
import {ModalModule} from 'ngx-bootstrap/modal';
import {BusyConfig, BusyModule} from 'angular2-busy';
import {ToastyModule, ToastyService} from 'ng2-toasty';
import {CustomFormsModule} from 'ng2-validation';
import {TooltipModule} from 'ngx-bootstrap/tooltip';

import {LayoutComponent} from './layout.component';
import {HeaderComponent} from './header/header.component';
import {NavigationTriggerComponent} from './header/navigation-trigger/navigation-trigger.component';

import {UserService} from '../../services/user/user.service';
import {ErrorForiddeComponent} from "../pages/error/403/403.component";
import {ErrorNotFoundComponent} from "../pages/error/404/404.component";
import {NavigationComponent} from "./header/navigation/navigation.component";
import {SystemMenuService} from "../../services/system/system.menu.service";
import {TranslateModule} from "@ngx-translate/core";

const PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
    suppressScrollX: true
};

export function busyConfigFactory() {
    return new BusyConfig({
        message: 'Data loading, please wait...',
        minDuration: 1000,
        backdrop: true,
    });
}

@NgModule({
    declarations: [
        LayoutComponent,
        HeaderComponent,
        NavigationComponent,
        NavigationTriggerComponent,
        ErrorForiddeComponent,
        ErrorNotFoundComponent
    ],
    imports: [
        CommonModule,
        LayoutRouting,
        FormsModule,
        BusyModule,
        TranslateModule,
        CustomFormsModule,
        TooltipModule.forRoot(),
        BsDropdownModule.forRoot(),
        ProgressbarModule.forRoot(),
        ButtonsModule.forRoot(),
        TabsModule.forRoot(),
        ModalModule.forRoot(),
        ToastyModule.forRoot(),
        PerfectScrollbarModule.forRoot(PERFECT_SCROLLBAR_CONFIG)
    ],
    providers: [
        UserService,
        {
            provide: BusyConfig,
            useFactory: busyConfigFactory
        },
        ToastyService,
        SystemMenuService
    ]
})

export class LayoutModule {
}
