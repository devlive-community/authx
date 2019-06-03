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
import {TableRowService} from "../../../../../services/table/table.row.service";
import {CommonPageModel} from "../../../../shared/model/common/response/page.model";
import {CodeConfig} from "../../../../../config/code.config";
import {SystemSettingsTableRowParam} from "../../../../shared/param/system/settings/system.settings.table.row.param";
import {SystemSettingsInterfaceParam} from "../../../../shared/param/system/settings/interface/system.settings.interface.param";
import {ModalDirective} from "ngx-bootstrap";
import {Select2Component, Select2OptionData} from "ng2-select2";
import {SystemMenuService} from "../../../../../services/system/system.menu.service";

@Component({
    selector: 'bootstack-system-settings-table',
    templateUrl: './system.settings.table.component.html'
})
export class SystemSettingsTableComponent implements OnInit {

    public loading: Subscription;
    public param; // 创建数据模型
    public page: CommonPageModel;
    public currentPage: number;
    public menus: any;
    // 数据信息
    public tableRows: Array<any> = [];
    public tableColumns: Array<any> = [];
    public config: any = {
        paging: true,
        sorting: {columns: this.tableColumns},
        filtering: {filterString: '', columnName: 'position'}
    };

    @ViewChild('createAndUpdateModal')
    public createAndUpdateModal: ModalDirective;

    multipleOptions: any = {
        multiple: true,
        dropdownAutoWidth: true,
        placeholder: '请选择使用的菜单',
        width: '100%',
        containerCssClass: 'select2-selection--alt',
        dropdownCssClass: 'select2-dropdown--alt'
    };
    methodOptions: Array<Select2OptionData>;
    @ViewChild('methodFields')
    methodFields: Select2Component;

    constructor(private router: Router,
                private translate: TranslateService,
                private tableRowService: TableRowService,
                private toastyService: ToastyService,
                private systemMenuService: SystemMenuService) {
        translate.addLangs(['zh-CN', 'en']);
        translate.setDefaultLang('zh-CN');
        let broswerLang = translate.getBrowserLang();
        translate.use(broswerLang.match(/en|zh-CN/) ? broswerLang : 'zh-CN');
        this.page = new CommonPageModel();
        this.param = new SystemSettingsTableRowParam();
        this.initMenu();
    }

    ngOnInit() {
    }

    /**
     * 初始化数据列表
     */
    initModels(page: CommonPageModel) {
        this.loading = this.tableRowService.getList(page).subscribe(
            response => {
                if (response.code !== CodeConfig.SUCCESS) {
                    this.toastyService.error(response.message);
                } else {
                    this.tableRows = response.data.content;
                    this.page = CommonPageModel.getPage(response.data);
                    this.currentPage = this.page.number;
                }
            }
        );
    }

    /**
     * 初始化菜单
     */
    initMenus() {
        let page = new CommonPageModel();
        page.size = 100;
        page.type = 3;
        this.loading = this.systemMenuService.getList(page).subscribe(
            response => {
                if (response.code !== CodeConfig.SUCCESS) {
                    this.toastyService.error(response.message);
                } else {
                    const temps = response.data.content;
                    this.methodOptions = this.generateOptions(temps);
                }
            }
        );
    }

    /**
     * 初始化当前菜单信息
     */
    initMenu() {
        this.systemMenuService.getByUrl(window.location.href.split("#")[1]).subscribe(
            response => {
                if (response.code !== CodeConfig.SUCCESS) {
                    this.toastyService.error(response.message);
                } else {
                    this.initTableRow(response.data);
                }
            }
        );
    }

    /**
     * 初始化表头信息
     */
    initTableRow(menu: any) {
        let page = new CommonPageModel();
        page.size = 20;
        this.tableRowService.getListByMenu(menu.id, page).subscribe(
            response => {
                if (response.code !== CodeConfig.SUCCESS) {
                    this.toastyService.error(response.message);
                } else {
                    this.tableColumns = response.data.content;
                    if (this.tableColumns) {
                        this.initModels(this.page);
                    }
                }
            }
        );
    }

    /**
     * 创建数据弹出框
     * @param model 数据模型
     */
    startShowCreateAndUpdateModal(model: any) {
        if (model) {
            this.param = model;
        } else {
            this.param = new SystemSettingsInterfaceParam();
        }
        this.initMenus();
        this.createAndUpdateModal.show();
    }

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

    methodChanged(data: { value: string[] }) {
        this.menus = data.value
    }

    createAndUpdate() {
        if (this.param.id) {
        } else {
            this.param.menus = this.menus;
            this.tableRowService.register(this.param).subscribe(
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
