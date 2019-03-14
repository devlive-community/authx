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
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { CustomFormsModule } from 'ng2-validation';
import { ToastyModule } from 'ng2-toasty';

import { UserLoginComponent } from './user.login.component';
import { UserService } from '../../../../services/user.service';

const USER_REGISTER_ROUTES: Routes = [
    { path: '', component: UserLoginComponent }
];

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        CustomFormsModule,
        TooltipModule.forRoot(),
        ToastyModule.forRoot(),
        RouterModule.forChild(USER_REGISTER_ROUTES)
    ],
    exports: [],
    declarations: [UserLoginComponent],
    providers: [
        UserService
    ],
})
export class UserLoginModule { }
