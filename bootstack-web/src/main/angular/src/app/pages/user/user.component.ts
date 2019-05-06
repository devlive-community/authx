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
import {CommonPageModel} from "../../shared/model/common/response/page.model";
import {CodeConfig} from "../../../config/code.config";
import {ModalDirective} from "ngx-bootstrap";
import {UserService} from "../../../services/user/user.service";
import {UserParam} from "../../shared/param/user/user.param";
import {SystemRoleService} from "../../../services/system/system.role.service";
import {Select2Component, Select2OptionData} from "ng2-select2";

@Component({
    selector: 'bootstack-user',
    templateUrl: './user.component.html'
})
export class UserComponent implements OnInit {

    public loading: Subscription;
    public roleLoading: Subscription;
    // menu list
    private models;
    // page model
    public page: CommonPageModel;
    // current page number
    public currentPage: number;

    @ViewChild('createAndUpdateModal')
    public createAndUpdateModal: ModalDirective;
    @ViewChild('assignmentRoleModal')
    public assignmentRoleModal: ModalDirective;

    public param: UserParam;
    public roleValues: any;
    // 已分配的权限数据
    roleAssignmentValues: any;

    multipleOptions: any = {
        multiple: true,
        dropdownAutoWidth: true,
        placeholder: '请选择需要分配的权限',
        width: '100%',
        containerCssClass: 'select2-selection--alt',
        dropdownCssClass: 'select2-dropdown--alt'
    };
    // 权限下拉数据
    methodRoleOptions: Array<Select2OptionData>;
    @ViewChild('methodRoles')
    methodRoles: Select2Component;

    constructor(private router: Router,
                private toastyService: ToastyService,
                private translate: TranslateService,
                private userService: UserService,
                private systemRoleService: SystemRoleService) {
        translate.addLangs(['zh-CN', 'en']);
        translate.setDefaultLang('zh-CN');
        let broswerLang = translate.getBrowserLang();
        translate.use(broswerLang.match(/en|zh-CN/) ? broswerLang : 'zh-CN');
        this.page = new CommonPageModel();
        this.param = new UserParam();
    }

    ngOnInit() {
        this.models = this.initList(this.page);
        this.roleValues = [];
        this.roleAssignmentValues = [];
    }

    /**
     * 初始化数据列表
     * @param page 分页信息
     */
    initList(page: CommonPageModel) {
        this.loading = this.userService.getList(page).subscribe(
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
     * 初始化权限列表
     */
    initRoleList() {
        this.page.size = 100;
        this.roleLoading = this.systemRoleService.getList(this.page).subscribe(
            response => {
                if (response.code !== CodeConfig.SUCCESS) {
                    this.toastyService.error(response.message);
                } else {
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
                    this.methodRoleOptions = fields;
                }
            }
        );
    }

    /**
     * 显示新增数据弹出框
     */
    startShowCreateAndUpdateModal(model: any) {
        if (model) {
            this.param = model;
        }
        this.createAndUpdateModal.show();
    }

    createAndUpdate() {
        if (this.param.id) {
            this.userService.update(this.param).subscribe(
                response => {
                    if (response.code !== CodeConfig.SUCCESS) {
                        this.toastyService.error(response.message);
                    } else {
                        this.initList(this.page);
                        this.createAndUpdateModal.hide();
                    }
                }
            );
        } else {
            this.userService.register(this.param).subscribe(
                response => {
                    if (response.code !== CodeConfig.SUCCESS) {
                        this.toastyService.error(response.message);
                    } else {
                        this.initList(this.page);
                        this.createAndUpdateModal.hide();
                    }
                }
            );
        }
    }

    /**
     * 分页信息改变重新加载数据
     * @param event 分页响应事件
     */
    pageChanged(event: any) {
        this.page.number = event.page;
        this.page.size = event.itemsPerPage;
        this.initList(this.page);
    }

    /**
     * 显示分配权限弹出框
     * @param user 需要分配的用户信息
     */
    startShowAssignmentRolesModal(user: any) {
        console.log(this.roleAssignmentValues);
        this.param = user;
        this.assignmentRoleModal.show();
        if (!this.methodRoleOptions) {
            this.initRoleList();
        }
    }

    /**
     * 分配用户权限
     */
    assignmentRoles() {
        let param = new UserParam();
        param.id = this.param.id;
        param.values = this.roleValues;
        this.userService.putRole(param).subscribe(
            response => {
                if (response.code !== CodeConfig.SUCCESS) {
                    this.toastyService.error(response.message);
                } else {
                    this.assignmentRoleModal.hide();
                    this.toastyService.error(response.message);
                    this.initList(this.page);
                }
            }
        );
    }

    /**
     * 数据选中情况
     * @param data 选中的数据
     */
    methodRoleChanged(data: { value: string[] }) {
        this.roleValues = data.value
    }

}
