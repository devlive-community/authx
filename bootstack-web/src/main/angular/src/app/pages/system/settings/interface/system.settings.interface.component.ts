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
import {ModalDirective} from "ngx-bootstrap";
import {Select2Component, Select2OptionData} from 'ng2-select2';
import {TranslateService} from '@ngx-translate/core';
import {CommonPageModel} from "../../../../shared/model/common/response/page.model";
import {SystemSettingsInterfaceService} from "../../../../../services/system/settings/system.settings.interface.service";
import {CodeConfig} from "../../../../../config/code.config";
import {SystemSettingsInterfaceParam} from "../../../../shared/param/system/settings/interface/system.settings.interface.param";
import {SystemSettingsMethodService} from "../../../../../services/system/settings/system.settings.method.service";

@Component({
    selector: 'bootstack-system-settings-interface',
    templateUrl: './system.settings.interface.component.html'
})
export class SystemSettingsInterfaceComponent implements OnInit {

    public loading: Subscription;
    // model list
    private models;
    // page model
    public page: CommonPageModel;
    // current page number
    public currentPage: number;
    // select tag config
    multipleOptions: any = {
        multiple: true,
        dropdownAutoWidth: true,
        placeholder: 'please select your option',
        width: '100%',
        containerCssClass: 'select2-selection--alt',
        dropdownCssClass: 'select2-dropdown--alt'
    };
    // select datas
    methodOptions: Array<Select2OptionData>;
    @ViewChild('methodFields')
    methodFields: Select2Component;
    // save method ids
    methodId: any;

    public param: SystemSettingsInterfaceParam;

    @ViewChild('createAndUpdateModal')
    public createAndUpdateModal: ModalDirective;

    constructor(private router: Router,
                private translate: TranslateService,
                private systemSettingsInterfaceService: SystemSettingsInterfaceService,
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
        this.loading = this.systemSettingsInterfaceService.getList(page).subscribe(
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
        this.loading = this.systemSettingsInterfaceService.getList(this.page).subscribe(
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
        // init method list
        this.page.size = 100;
        this.systemSettingsMethodService.getList(this.page).subscribe(
            response => {
                const fields = [], temps = response.data.content;
                for (const x in temps) {
                    let content = temps[x].content;
                    if (!content) {
                        content = temps[x].name
                    }
                    const v = {
                        "id": temps[x].id,
                        "text": content
                    }
                    fields.push(v);
                }
                this.methodOptions = fields;
            }
        )
    }

    createAndUpdate() {
        if (this.param.id) {
            this.param.method = this.methodId;
            this.systemSettingsInterfaceService.update(this.param).subscribe(
                response => {
                    if (response.code !== CodeConfig.SUCCESS) {
                        this.toastyService.error(response.message);
                    } else {
                        this.initModels(this.page);
                        this.createAndUpdateModal.hide();
                    }
                }
            );
        } else {
            this.param.method = this.methodId;
            this.systemSettingsInterfaceService.register(this.param).subscribe(
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

    // select method change
    methodChanged(data: { value: string[] }) {
        this.methodId = data.value
    }

}
