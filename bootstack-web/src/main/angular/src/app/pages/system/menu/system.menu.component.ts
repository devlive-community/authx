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
import {Select2Component, Select2OptionData} from 'ng2-select2';
import {CommonPageModel} from "../../../shared/model/common/response/page.model";
import {CodeConfig} from "../../../../config/code.config";
import {ModalDirective, PopoverDirective} from "ngx-bootstrap";
import {SystemMenuService} from "../../../../services/system/system.menu.service";
import {SystemMenuParam} from "../../../shared/param/system/menu/system.menu.param";
import {SystemSettingsMethodService} from "../../../../services/system/settings/system.settings.method.service";
import {SystemMenuTypeService} from "../../../../services/system/system.menu.type.service";
import {IconService} from "../../../../services/icon/icon.service";

@Component({
    selector: 'bootstack-system-menu',
    templateUrl: './system.menu.component.html'
})
export class SystemMenuComponent implements OnInit {

    public loading: Subscription;
    public iconLoading: Subscription;
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
        placeholder: '请选择请求方式',
        width: '100%',
        containerCssClass: 'select2-selection--alt',
        dropdownCssClass: 'select2-dropdown--alt'
    };
    methodOptions: Array<Select2OptionData>;
    @ViewChild('methodFields')
    methodFields: Select2Component;
    menuTypeOptions: any;
    menuParentOptions: any;

    @ViewChild('createAndUpdateModal')
    public createAndUpdateModal: ModalDirective;

    @ViewChild('popIcon')
    public popIcon: PopoverDirective;

    public types: any;
    public iconModels;
    public iconRadio;

    private methodType;

    // model param info
    public param: SystemMenuParam;

    constructor(private router: Router,
                private toastyService: ToastyService,
                private translate: TranslateService,
                private systemSettingsMethodService: SystemSettingsMethodService,
                private systemMenuTypeService: SystemMenuTypeService,
                private systemMenuService: SystemMenuService,
                private iconService: IconService) {
        translate.addLangs(['zh-CN', 'en']);
        translate.setDefaultLang('zh-CN');
        let broswerLang = translate.getBrowserLang();
        translate.use(broswerLang.match(/en|zh-CN/) ? broswerLang : 'zh-CN');
        this.page = new CommonPageModel();
        this.param = new SystemMenuParam();
        // TODO: fix this bug
        this.page.type = 1;
    }

    ngOnInit() {
        this.models = this.initList(this.page, 1);
        this.initMenuType();
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
        // this.initMenuParent(0);
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
    initMenuParent(type: number) {
        this.page.size = 100;
        this.systemMenuService.getListByParent(this.page, 0, type).subscribe(
            response => {
                const temps = response.data.content;
                this.menuParentOptions = this.generateOptions(temps);
            }
        )
    }

    /**
     * 初始化图标数据
     * @param page 分页信息
     */
    initIconList(page: CommonPageModel) {
        this.iconLoading = this.iconService.getList(page).subscribe(
            response => {
                if (response.code !== CodeConfig.SUCCESS) {
                    this.toastyService.error(response.message);
                } else {
                    this.iconModels = response.data.content;
                }
            }
        );
    }

    /**
     * generate select options
     */
    generateOptions(temps) {
        const fields = [];
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
        this.page.type = 1;
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
        this.page.type = this.methodType;
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
        this.param.method = data.value;
    }

    menuTypeChange(data: any) {
        this.param.type = data;
        this.initMenuParent(data);
    }

    menuParentChange(data: any) {
        this.param.parent = data;
    }

    selectType(type: any) {
        this.page.type = type.id;
        this.methodType = type.id;
        this.models = this.initList(this.page, 1);
    }

    /**
     * 启动加载图标列表
     */
    startIconPopover() {
        let param = new CommonPageModel();
        param.size = 100;
        param.number = 1;
        this.initIconList(param);
    }

    /**
     * 图标修改
     * @param data icon图标信息
     */
    radioChange(data: any) {
        this.param.icon = data.code;
        this.param.iconId = data.id;
    }

}
