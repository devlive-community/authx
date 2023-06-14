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
import {ArchwizardModule} from 'ng2-archwizard';
import {Select2Module} from 'ng2-select2';
import {TranslateModule} from '@ngx-translate/core';
import {TabsModule} from 'ngx-bootstrap/tabs';
import {PopoverModule} from 'ngx-bootstrap/popover';

import {ModalModule, PaginationModule} from "ngx-bootstrap";
import {SystemMenuComponent} from "./system.menu.component";
import {SystemMenuService} from "../../../../services/system/system.menu.service";
import {SystemSettingsMethodService} from "../../../../services/system/settings/system.settings.method.service";
import {SystemMenuTypeService} from "../../../../services/system/system.menu.type.service";
import {IconService} from "../../../../services/icon/icon.service";

const SYSTEM_MENU_ROUTES: Routes = [
    {path: '', component: SystemMenuComponent}
];

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        CustomFormsModule,
        BusyModule,
        ArchwizardModule,
        Select2Module,
        TranslateModule,
        TooltipModule.forRoot(),
        TabsModule.forRoot(),
        ToastyModule.forRoot(),
        PaginationModule.forRoot(),
        ModalModule.forRoot(),
        PopoverModule.forRoot(),
        RouterModule.forChild(SYSTEM_MENU_ROUTES)
    ],
    exports: [],
    declarations: [
        SystemMenuComponent
    ],
    providers: [
        SystemMenuService,
        SystemSettingsMethodService,
        SystemMenuTypeService,
        IconService
    ],
})
export class SystemMenuModule {
}
