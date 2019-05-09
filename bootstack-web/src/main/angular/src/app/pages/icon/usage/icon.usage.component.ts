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
import {CommonPageModel} from "../../../shared/model/common/response/page.model";
import {CodeConfig} from "../../../../config/code.config";
import {ModalDirective} from "ngx-bootstrap";
import {SystemMenuTypeParam} from "../../../shared/param/system/menu/system.menu.type.param";
import {IconUsageService} from "../../../../services/icon/icon.usage.service";
import {IconUsageParam} from "../../../shared/param/icon/icon.usage.param";

@Component({
    selector: 'bootstack-icon-usage',
    templateUrl: './icon.usage.component.html'
})
export class IconUsageComponent implements OnInit {

    public loading: Subscription;
    public page: CommonPageModel;
    public currentPage: number;
    @ViewChild('createAndUpdateModal')
    public createAndUpdateModal: ModalDirective;
    public param: IconUsageParam;
    private models;

    constructor(private router: Router,
                private toastyService: ToastyService,
                private translate: TranslateService,
                private service: IconUsageService) {
        translate.addLangs(['zh-CN', 'en']);
        translate.setDefaultLang('zh-CN');
        let broswerLang = translate.getBrowserLang();
        translate.use(broswerLang.match(/en|zh-CN/) ? broswerLang : 'zh-CN');
        this.page = new CommonPageModel();
        this.param = new SystemMenuTypeParam();
    }

    ngOnInit() {
        this.models = this.initList(this.page, 1);
    }

    initList(page: CommonPageModel, uid: number) {
        this.loading = this.service.getList(page).subscribe(
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
            this.param = new SystemMenuTypeParam();
        }
        this.createAndUpdateModal.show();
    }

    createAndUpdate() {
        if (this.param.id) {
            this.service.update(this.param).subscribe(
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
            this.service.register(this.param).subscribe(
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
        this.loading = this.service.getList(this.page).subscribe(
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

}
