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

@Component({
    selector: 'bootstack-dashboard-index',
    templateUrl: './dashboard.index.component.html',
    styleUrls: [
        './dashboard.index.component.scss'
    ]
})

export class DashboardIndexComponent implements OnInit {

    options: any;
    quickStatChartOptions: any; // Quick Stats Chart Options
    quickStatChartData: any; // Quick Stats Chart Data

    constructor() {
        this.quickStatChartData = [
            {
                title: '用户总数',
                value: '987,459',
                color: 'light-blue'
            },
            {
                title: '菜单总数',
                value: '300',
                color: 'amber'
            },
            {
                title: '日志总数',
                value: '458,778',
                color: 'purple'
            },
            {
                title: '建设中',
                value: '建设中',
                color: 'red'
            }
        ];
        this.quickStatChartOptions = {
            type: 'bar',
            height: '36px',
            barWidth: 3,
            barColor: 'rgba(255,255,255,0.8)',
            barSpacing: 2
        }
    }

    ngOnInit() {
        const xAxisData = [];
        const data1 = [];
        const data2 = [];

        for (let i = 0; i < 100; i++) {
            xAxisData.push('category' + i);
            data1.push((Math.sin(i / 5) * (i / 5 - 10) + i / 6) * 5);
            data2.push((Math.cos(i / 5) * (i / 5 - 10) + i / 6) * 5);
        }

        this.options = {
            legend: {
                data: ['bar', 'bar2'],
                align: 'left'
            },
            tooltip: {},
            xAxis: {
                data: xAxisData,
                silent: false,
                splitLine: {
                    show: false
                }
            },
            yAxis: {},
            series: [{
                name: 'bar',
                type: 'bar',
                data: data1,
                animationDelay: function (idx) {
                    return idx * 10;
                }
            }, {
                name: 'bar2',
                type: 'bar',
                data: data2,
                animationDelay: function (idx) {
                    return idx * 10 + 100;
                }
            }],
            animationEasing: 'elasticOut',
            animationDelayUpdate: function (idx) {
                return idx * 5;
            }
        };
    }

}
