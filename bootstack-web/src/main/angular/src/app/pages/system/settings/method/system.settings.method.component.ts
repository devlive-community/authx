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
import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {ToastyService} from 'ng2-toasty';
import {Subscription} from "rxjs";
import {TranslateService} from '@ngx-translate/core';
import {ModalDirective} from "ngx-bootstrap";
import {CommonPageModel} from "../../../../shared/model/common/response/page.model";
import {CodeConfig} from "../../../../../config/code.config";
import {SystemSettingsInterfaceParam} from "../../../../shared/param/system/settings/interface/system.settings.interface.param";
import {SystemSettingsMethodService} from "../../../../../services/system/settings/system.settings.method.service";
import {SystemSettingsMethodParam} from "../../../../shared/param/system/settings/method/system.settings.method.param";

@Component({
    selector: 'bootstack-system-settings-method',
    templateUrl: './system.settings.method.component.html'
})
export class SystemSettingsMethodComponent implements OnInit {

    public loading: Subscription;
    // model list
    private models;
    // page model
    public page: CommonPageModel;
    // current page number
    public currentPage: number;

    public param: SystemSettingsMethodParam;

    @ViewChild('createAndUpdateModal')
    public createAndUpdateModal: ModalDirective;

    constructor(private router: Router,
                private translate: TranslateService,
                private systemSettingsMethodService: SystemSettingsMethodService,
                private toastyService: ToastyService) {
        translate.addLangs(['zh-CN', 'en']);
        translate.setDefaultLang('zh-CN');
        let broswerLang = translate.getBrowserLang();
        translate.use(broswerLang.match(/en|zh-CN/) ? broswerLang : 'zh-CN');
        this.page = new CommonPageModel();
        this.param = new SystemSettingsInterfaceParam();
    }

    ngOnInit() {
        this.models = this.initModels(this.page);
    }

    /**
     * init all models
     */
    initModels(page: CommonPageModel) {
        this.loading = this.systemSettingsMethodService.getList(page).subscribe(
            response => {
                if (response.code !== CodeConfig.SUCCESS) {
                    this.toastyService.error(response.message);
                } else {
                    this.models = response.data.content;
                    this.page = CommonPageModel.getPage(response.data);
                    this.currentPage = this.page.number;
                }
            }
        );
    }

    /**
     * page event
     * @param event page event
     */
    pageChanged(event: any) {
        this.page.number = event.page;
        this.page.size = event.itemsPerPage;
        this.loading = this.systemSettingsMethodService.getList(this.page).subscribe(
            response => {
                if (response.code !== CodeConfig.SUCCESS) {
                    this.toastyService.error(response.message);
                } else {
                    this.models = response.data.content;
                    this.page = CommonPageModel.getPage(response.data);
                    this.currentPage = this.page.number;
                }
            }
        );
    }

    /**
     * show modal
     */
    startShowCreateAndUpdateModal(model: any) {
        if (model) {
            this.param = model;
        } else {
            this.param = new SystemSettingsInterfaceParam();
        }
        this.createAndUpdateModal.show();
    }

    createAndUpdate() {
        if (this.param.id) {
            this.systemSettingsMethodService.update(this.param).subscribe(
                response => {
                    if (response.code !== CodeConfig.SUCCESS) {
                        this.toastyService.error(response.message);
                    } else {
                        this.toastyService.success(response.message);
                        this.initModels(this.page);
                        this.createAndUpdateModal.hide();
                    }
                }
            );
        } else {
            this.systemSettingsMethodService.register(this.param).subscribe(
                response => {
                    if (response.code !== CodeConfig.SUCCESS) {
                        this.toastyService.error(response.message);
                    } else {
                        this.initModels(this.page);
                        this.createAndUpdateModal.hide();
                    }
                }
            );
        }
    }

}
