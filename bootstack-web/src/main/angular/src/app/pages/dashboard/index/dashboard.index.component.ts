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
import {OverviewService} from "../../../../services/overview/overview.service";
import {CodeConfig} from "../../../../config/code.config";
import {ToastyService} from "ng2-toasty";
import {Subscription} from "rxjs";

@Component({
    selector: 'bootstack-dashboard-index',
    templateUrl: './dashboard.index.component.html',
    styleUrls: [
        './dashboard.index.component.scss'
    ]
})

export class DashboardIndexComponent implements OnInit {

    loading: Subscription;
    options: any;
    quickStatChartOptions: any; // Quick Stats Chart Options
    models: any; // Quick Stats Chart Data

    constructor(private toastyService: ToastyService,
                private overviewService: OverviewService) {
        this.quickStatChartOptions = {
            type: 'bar',
            height: '36px',
            barWidth: 3,
            barColor: 'rgba(255,255,255,0.8)',
            barSpacing: 2
        }
    }

    ngOnInit() {
        this.initCount();
    }

    initCount() {
        this.loading = this.overviewService.getCount().subscribe(
            response => {
                if (response.code !== CodeConfig.SUCCESS) {
                    this.toastyService.error(response.message);
                } else {
                    this.models = response.data;
                }
            }
        );
    }

}
