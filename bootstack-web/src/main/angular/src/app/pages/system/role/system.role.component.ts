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
import {SystemRoleService} from "../../../../services/system/system.role.service";
import {CommonPageModel} from "../../../shared/model/common/response/page.model";
import {Subscription} from "rxjs";
import {CodeConfig} from "../../../../config/code.config";

@Component({
    selector: 'bootstack-system-role',
    templateUrl: './system.role.component.html'
})
export class SystemRoleComponent implements OnInit {

    public loadArticleBusy: Subscription;
    // role list
    private roles;
    // page model
    public page: CommonPageModel;
    // current page number
    public currentPage: number;

    constructor(private router: Router,
                private systemRoleService: SystemRoleService,
                private toastyService: ToastyService) {
        this.page = new CommonPageModel();
    }

    ngOnInit() {
        this.roles = this.initRoles(this.page);
    }

    /**
     * get all system role
     */
    initRoles(page: CommonPageModel) {
        this.loadArticleBusy = this.systemRoleService.getList(page).subscribe(
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
     * page event
     * @param event page event
     */
    pageChanged(event: any) {
        this.page.number = event.page;
        this.page.size = event.itemsPerPage;
        this.loadArticleBusy = this.systemRoleService.getList(this.page).subscribe(
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

}
