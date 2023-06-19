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
}
