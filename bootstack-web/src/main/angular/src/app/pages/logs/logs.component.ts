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
import {SystemLogService} from "../../../services/system/system.log.service";
import {CommonPageModel} from "../../shared/model/common/response/page.model";
import {CodeConfig} from "../../../config/code.config";

@Component({
    selector: 'bootstack-logs',
    templateUrl: './logs.component.html'
})
export class LogsComponent implements OnInit {

    public loading: Subscription;
    public detailsLoading: Subscription;
    // menu list
    private models;
    // page model
    public page: CommonPageModel;
    // current page number
    public currentPage: number;

    @ViewChild('showDetailModal')
    public showDetailModal: ModalDirective;

    // 详情数据
    public details;

    constructor(private router: Router,
                private toastyService: ToastyService,
                private translate: TranslateService,
                private systemLogService: SystemLogService) {
        translate.addLangs(['zh-CN', 'en']);
        translate.setDefaultLang('zh-CN');
        let broswerLang = translate.getBrowserLang();
        translate.use(broswerLang.match(/en|zh-CN/) ? broswerLang : 'zh-CN');
        this.page = new CommonPageModel();
    }

    ngOnInit() {
        this.models = this.initList(this.page, 1);
    }

    initList(page: CommonPageModel, uid: number) {
        this.loading = this.systemLogService.getList(page).subscribe(
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

    pageChanged(event: any) {
        this.page.number = event.page;
        this.page.size = event.itemsPerPage;
        this.loading = this.systemLogService.getList(this.page).subscribe(
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
     * 显示详情弹出框
     * @param model 数据信息,唯一标志
     */
    startShowDetailModal(model: any) {
        this.detailsLoading = this.systemLogService.getInfo(model.id).subscribe(
            response => {
                if (response.code !== CodeConfig.SUCCESS) {
                    this.toastyService.error(response.message);
                } else {
                    this.details = response.data;
                }
            }
        );
        this.showDetailModal.show();
    }

}
