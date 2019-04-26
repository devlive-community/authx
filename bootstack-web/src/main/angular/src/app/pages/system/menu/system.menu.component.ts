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
import {Select2Component, Select2OptionData} from 'ng2-select2';
import {CommonPageModel} from "../../../shared/model/common/response/page.model";
import {CodeConfig} from "../../../../config/code.config";
import {ModalDirective} from "ngx-bootstrap";
import {SystemMenuService} from "../../../../services/system/system.menu.service";
import {SystemMenuParam} from "../../../shared/param/system/menu/system.menu.param";
import {SystemSettingsMethodService} from "../../../../services/system/settings/system.settings.method.service";
import {SystemMenuTypeService} from "../../../../services/system/system.menu.type.service";

@Component({
    selector: 'bootstack-system-menu',
    templateUrl: './system.menu.component.html'
})
export class SystemMenuComponent implements OnInit {

    public loading: Subscription;
    // menu list
    public models;
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
    methodOptions: Array<Select2OptionData>;
    @ViewChild('methodFields')
    methodFields: Select2Component;
    menuTypeOptions: Array<Select2OptionData>;
    @ViewChild('menuTypeFields')
    menuTypeFields: Select2Component;
    menuParentOptions: Array<Select2OptionData>;
    @ViewChild('menuParentFields')
    menuParentFields: Select2Component;

    @ViewChild('createAndUpdateModal')
    public createAndUpdateModal: ModalDirective;

    // model param info
    public param: SystemMenuParam;

    constructor(private router: Router,
                private toastyService: ToastyService,
                private systemSettingsMethodService: SystemSettingsMethodService,
                private systemMenuTypeService: SystemMenuTypeService,
                private systemMenuService: SystemMenuService) {
        this.page = new CommonPageModel();
        this.param = new SystemMenuParam();
    }

    ngOnInit() {
        this.models = this.initList(this.page, 1);
    }

    initList(page: CommonPageModel, uid: number) {
        this.loading = this.systemMenuService.getList(page).subscribe(
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
            this.param = new SystemMenuParam();
        }
        this.createAndUpdateModal.show();
    }

    /**
     * init step data models
     * @param event current event
     */
    initStepDataModels(event) {
        this.initMethod();
        this.initMenuType();
        this.initMenuParent();
    }

    /**
     * init method list
     */
    initMethod() {
        this.page.size = 100;
        this.systemSettingsMethodService.getList(this.page).subscribe(
            response => {
                const temps = response.data.content;
                this.methodOptions = this.generateOptions(temps);
            }
        )
    }

    /**
     * init menu type list
     */
    initMenuType() {
        this.page.size = 100;
        this.systemMenuTypeService.getList(this.page).subscribe(
            response => {
                const temps = response.data.content;
                this.menuTypeOptions = this.generateOptions(temps);
            }
        )
    }

    /**
     * init menu parent list
     */
    initMenuParent() {
        this.page.size = 100;
        this.systemMenuService.getListByParent(this.page, 0).subscribe(
            response => {
                const temps = response.data.content;
                this.menuParentOptions = this.generateOptions(temps);
            }
        )
    }

    /**
     * generate select options
     */
    generateOptions(temps) {
        const fields = [];
        fields.push({
            'id': 0,
            'text': 'please select data'
        })
        for (const x in temps) {
            let content = temps[x].name;
            const v = {
                "id": temps[x].id,
                "text": content
            }
            fields.push(v);
        }
        return fields;
    }

    createAndUpdate() {
        console.log(this.param);
        if (this.param.type === '0') {
            this.toastyService.error('menu type is must null');
            return
        }
        if (this.param.id) {
            this.systemMenuService.update(this.param).subscribe(
                response => {
                    if (response.code !== CodeConfig.SUCCESS) {
                        this.toastyService.error(response.message);
                    } else {
                        this.initList(this.page, 1);
                        this.createAndUpdateModal.hide();
                    }
                }
            );
        } else {
            this.systemMenuService.register(this.param).subscribe(
                response => {
                    if (response.code !== CodeConfig.SUCCESS) {
                        this.toastyService.error(response.message);
                    } else {
                        this.initList(this.page, 1);
                        this.createAndUpdateModal.hide();
                    }
                }
            );
        }
    }

    pageChanged(event: any) {
        this.page.number = event.page;
        this.page.size = event.itemsPerPage;
        this.loading = this.systemMenuService.getList(this.page).subscribe(
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

    // select method change
    methodChanged(data: { value: string[] }) {
        this.param.method = data.value
    }

    menuTypeChanged(data: { value: string[] }) {
        this.param.type = data.value
    }

    menuParentChanged(data: { value: string[] }) {
        this.param.parent = data.value
    }

}
