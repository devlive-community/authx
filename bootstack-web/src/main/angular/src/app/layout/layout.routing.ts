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
import {RouterModule, Routes} from '@angular/router';
import {LayoutComponent} from './layout.component';
import {ErrorForiddeComponent} from "../pages/error/403/403.component";
import {ErrorNotFoundComponent} from "../pages/error/404/404.component";
import {AuthGuard} from "../auth/auth.guard";

const LAYOUT_ROUTES: Routes = [
    {
        path: '', component: LayoutComponent, children: [
            {path: '', redirectTo: 'home', pathMatch: 'full'},
            {path: 'home', loadChildren: '../pages/home/home.module#HomeModule'},
        ]
    },
    {
        path: 'user', children: [
            {path: 'register', loadChildren: '../pages/user/register/user.register.module#UserRegisterModule'},
            {path: 'login', loadChildren: '../pages/user/login/user.login.module#UserLoginModule'},
        ]
    },
    {
        path: 'error', children: [
            {path: '403', component: ErrorForiddeComponent},
            {path: '404', component: ErrorNotFoundComponent}
        ]
    },
    {
        path: 'dashboard', component: LayoutComponent, canActivate: [AuthGuard], children: [
            {path: '', redirectTo: 'index', pathMatch: 'full'},
            {path: 'index', loadChildren: '../pages/dashboard/index/dashboard.index.module#DashboardIndexModule'},
        ]
    },
    {
        path: 'system', component: LayoutComponent, canActivate: [AuthGuard], children: [
            {path: '', redirectTo: 'index', pathMatch: 'full'},
            {path: 'role', loadChildren: '../pages/system/role/system.role.module#SystemRoleModule'},
            {path: 'menu-type', loadChildren: '../pages/system/menu/system.menu.type.module#SystemMenuTypeModule'},
            {path: 'menu', loadChildren: '../pages/system/menu/system.menu.module#SystemMenuModule'},
            {
                path: 'settings', children: [
                    {
                        path: 'interface',
                        loadChildren: '../pages/system/settings/interface/system.settings.interface.module#SystemSettingsInterfaceModule'
                    },
                    {
                        path: 'method',
                        loadChildren: '../pages/system/settings/method/system.settings.method.module#SystemSettingsMethodModule'
                    }
                ]
            }
        ]
    },
    {
        path: 'userManager', component: LayoutComponent, canActivate: [AuthGuard], children: [
            {path: '', redirectTo: 'index', pathMatch: 'full'},
            {path: 'users', loadChildren: '../pages/user/user.module#UserModule'},
            {path: 'menu-type', loadChildren: '../pages/system/menu/system.menu.type.module#SystemMenuTypeModule'},
        ]
    },
    {
        path: 'logManager', component: LayoutComponent, canActivate: [AuthGuard], children: [
            {path: '', redirectTo: 'index', pathMatch: 'full'},
            {path: 'logs', loadChildren: '../pages/logs/logs.module#LogsModule'},
            {path: 'logs/type', loadChildren: '../pages/logs/type/logs.type.module#LogsTypeModule'},
        ]
    },
    {
        path: 'iconManager', component: LayoutComponent, canActivate: [AuthGuard], children: [
            {path: '', redirectTo: 'index', pathMatch: 'full'},
            {path: 'icon', loadChildren: '../pages/icon/icon.module#IconModule'},
            {path: 'icon/type', loadChildren: '../pages/icon/type/icon.type.module#IconTypeModule'},
        ]
    },
];

export const LayoutRouting = RouterModule.forChild(LAYOUT_ROUTES);
