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
import {TranslateService} from '@ngx-translate/core';
import {SystemRoleService} from "../../../../services/system/system.role.service";
import {CommonPageModel} from "../../../shared/model/common/response/page.model";
import {Subscription} from "rxjs";
import {TreeMode} from 'tree-ngx';
import {CodeConfig} from "../../../../config/code.config";
import {ModalDirective} from "ngx-bootstrap";
import {SystemRoleParam} from "../../../shared/param/system/role/system.role.param";
import {SystemMenuRoleParam} from "../../../shared/param/system/menu/system.menu.role.param";
import {SystemMenuTypeService} from "../../../../services/system/system.menu.type.service";
import {SystemRoleMenuParam} from "../../../shared/param/system/role/system.role.menu.param";

@Component({
    selector: 'bootstack-system-role',
    templateUrl: './system.role.component.html'
})
export class SystemRoleComponent implements OnInit {

    public loading: Subscription;
    public mtLoading: Subscription;
    // role list
    private roles;
    // page model
    public page: CommonPageModel;
    // current page number
    public currentPage: number;
    // menu type
    public types: any;
    public treeNodes: any;

    public menuNodesJson: any;

    public treeOptions = {
        mode: TreeMode.MultiSelect,
        checkboxes: true,
        alwaysEmitSelected: true
    }

    // system role param info
    public param: SystemRoleParam;
    public menuAndType: SystemMenuRoleParam;

    @ViewChild('createAndUpdateModal')
    public createAndUpdateModal: ModalDirective;
    @ViewChild('assignmentMenuModal')
    public assignmentMenuModal: ModalDirective;

    constructor(private router: Router,
                private translate: TranslateService,
                private systemRoleService: SystemRoleService,
                private systemMenuTypeService: SystemMenuTypeService,
                private toastyService: ToastyService) {
        translate.addLangs(['zh-CN', 'en']);
        translate.setDefaultLang('zh-CN');
        let broswerLang = translate.getBrowserLang();
        translate.use(broswerLang.match(/en|zh-CN/) ? broswerLang : 'zh-CN');
        this.page = new CommonPageModel();
        this.param = new SystemRoleParam();
        this.menuAndType = new SystemMenuRoleParam();
        // TODO: fix this bug
        this.menuAndType.menuType = 1;
    }

    ngOnInit() {
        this.roles = this.initRoles(this.page);
    }

    /**
     * get all system role
     */
    initRoles(page: CommonPageModel) {
        this.loading = this.systemRoleService.getList(page).subscribe(
            response => {
                if (response.code !== CodeConfig.SUCCESS) {
                    this.toastyService.error(response.message);
                } else {
                    this.roles = response.data.content;
                    this.page = CommonPageModel.getPage(response.data);
                    this.currentPage = this.page.number;
                }
            }
        );
    }

    initRoleMenuTree() {
        this.menuAndType.role = this.param.id;
        this.mtLoading = this.systemRoleService.getTreeListByRoleAndType(this.menuAndType).subscribe(
            response => {
                if (response.code !== CodeConfig.SUCCESS) {
                    this.toastyService.error(response.message);
                } else {
                    this.treeNodes = response.data;
                }
            }
        );
    }

    initMenuType() {
        this.systemMenuTypeService.getList(this.page).subscribe(
            response => {
                if (response.code !== CodeConfig.SUCCESS) {
                    this.toastyService.error(response.message);
                } else {
                    this.types = response.data.content;
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
        this.loading = this.systemRoleService.getList(this.page).subscribe(
            response => {
                if (response.code !== CodeConfig.SUCCESS) {
                    this.toastyService.error(response.message);
                } else {
                    this.roles = response.data.content;
                    this.page = CommonPageModel.getPage(response.data);
                    this.currentPage = this.page.number;
                }
            }
        );
    }

    /**
     * show modal
     */
    startShowCreateAndUpdateModal(role: any) {
        if (role) {
            this.param = role;
        } else {
            this.param = new SystemRoleParam();
        }
        this.createAndUpdateModal.show();
    }

    startShowAssignmentMenusModal(role: any) {
        this.param = role;
        this.assignmentMenuModal.show();
        this.initMenuType();
        this.initRoleMenuTree();
    }

    createAndUpdate() {
        if (this.param.id) {
            // update exists role
            this.systemRoleService.update(this.param).subscribe(
                response => {
                    if (response.code !== CodeConfig.SUCCESS) {
                        this.toastyService.error(response.message);
                    } else {
                        this.initRoles(this.page);
                        this.createAndUpdateModal.hide();
                    }
                }
            );
        } else {
            // create new role
            this.systemRoleService.register(this.param).subscribe(
                response => {
                    if (response.code !== CodeConfig.SUCCESS) {
                        this.toastyService.error(response.message);
                    } else {
                        this.initRoles(this.page);
                        this.createAndUpdateModal.hide();
                    }
                }
            );
        }
    }

    selectType(type: any) {
        this.menuAndType.menuType = type.id;
        this.initRoleMenuTree();
    }

    /**
     * 分配权限
     */
    assignmentMenus() {
        console.log(this.menuNodesJson);
        let menuNodes = [];
        for (var i in this.menuNodesJson) {
            menuNodes.push(this.menuNodesJson[i].phrase);
        }
        let param = new SystemRoleMenuParam();
        param.key = this.param.id;
        param.value = menuNodes;
        param.menuType = this.menuAndType.menuType;
        this.systemRoleService.updateRoleMenu(param).subscribe(
            response => {
                if (response.code !== CodeConfig.SUCCESS) {
                    this.toastyService.error(response.message);
                } else {
                    this.assignmentMenuModal.hide();
                    this.toastyService.error(response.message);
                }
            }
        );
    }

}
