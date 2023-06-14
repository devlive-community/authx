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
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ToastyService} from 'ng2-toasty';
import {Subscription} from "rxjs";
import {TranslateService} from '@ngx-translate/core';

@Component({
    selector: 'bootstack-system-settings',
    templateUrl: './system.settings.component.html'
})
export class SystemSettingsComponent implements OnInit {

    public loading: Subscription;
    // 系统设置信息
    private model;
    public param;

    constructor(private router: Router,
                private translate: TranslateService,
                // private systemSettingsMethodService: SystemSettingsMethodService,
                private toastyService: ToastyService) {
        translate.addLangs(['zh-CN', 'en']);
        translate.setDefaultLang('zh-CN');
        let broswerLang = translate.getBrowserLang();
        translate.use(broswerLang.match(/en|zh-CN/) ? broswerLang : 'zh-CN');
    }

    ngOnInit() {
    }

}
