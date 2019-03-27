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
import {CommonPageModel} from "../../../../shared/model/common/response/page.model";
import {SystemSettingsInterfaceService} from "../../../../../services/system/settings/system.settings.interface.service";
import {CodeConfig} from "../../../../../config/code.config";
import {SystemSettingsInterfaceParam} from "../../../../shared/param/system/settings/interface/system.settings.interface.param";
import {SystemRoleParam} from "../../../../shared/param/system/role/system.role.param";

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

    public param: SystemSettingsInterfaceParam;

    @ViewChild('createAndUpdateModal')
    public createAndUpdateModal: ModalDirective;

    constructor(private router: Router,
                private systemSettingsInterfaceService: SystemSettingsInterfaceService,
                private toastyService: ToastyService) {
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
    }

}
