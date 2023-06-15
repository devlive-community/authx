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
import {AfterViewInit, Component, EventEmitter, Input, OnDestroy, OnInit, Output, ViewChild} from '@angular/core';
import {ZtreeComponent} from './ztree.component';

declare var $: any;

@Component({
    selector: 'bootstack-select-ztree',
    templateUrl: './select-ztree.component.html',
    styleUrls: ['./select-ztree.component.scss']
})
export class SelectZtreeComponent implements AfterViewInit, OnDestroy, OnInit {

    @ViewChild('selectZtree') selectZtree: ZtreeComponent;

    //配置
    @Input() setting: any = {};

    //输入数据
    @Input() zNodes: any = [];

    //方向
    @Input() direction: string = 'dropdown';

    //placeholder
    @Input() placeholder: string = '请选择...';

    //是否开启搜索
    @Input() enableSearch: boolean = true;

    //确认事件
    @Output()
    onApprove = new EventEmitter();

    //是否关闭
    isCollapsed: boolean = true;

    //显示checked
    showChecked: boolean;

    //名称
    names: string;

    //搜索文本
    searchTxt: string;

    constructor() {

    }

    ngOnInit() {
        if (this.setting && this.setting.check && this.setting.check.enable) {
            this.showChecked = true;
        } else {
            this.showChecked = false;
            //当参数中setting中配置了onClick事件
            if (this.setting && this.setting.callback && this.setting.callback.onClick) {
                let oldClick = this.setting.callback.onClick;
                let that = this;
                this.setting.callback.onClick = function (event, treeId, treeNode, clickFlag) {
                    that.isCollapsed = true;
                    treeNode.selected = true;
                    that.names = treeNode.name;
                    oldClick(event, treeId, treeNode, clickFlag);
                }
            } else {
                let that = this;
                this.setting.callback.onClick = function (event, treeId, treeNode, clickFlag) {
                    that.isCollapsed = true;
                    treeNode.selected = true;
                    that.names = treeNode.name;
                }
            }
        }
    }

    ngAfterViewInit() {

    }

    /**
     * 销毁
     */
    ngOnDestroy() {
        this.selectZtree.destroyTree();
    }

    /**
     * 树初始化
     */
    treeViewInit() {
        if (this.showChecked) {
            this.setCheckedNames();
        } else {
            //判断自定义属性selected是否为true,多个时取最后一个
            this.names = '';
            let nodes = this.selectZtree.getTreeInstance().getNodes();
            if (nodes && nodes.length > 0) {
                let nodeArray = this.selectZtree.getTreeInstance().transformToArray(nodes);
                for (let i = nodeArray.length - 1; i >= 0; i--) {
                    if (nodeArray[i].selected) {
                        this.names = nodeArray[i].name;
                        break;
                    }
                }
            }
        }
    }

    /**
     * 确定
     */
    approve() {
        let nodeArray = this.setCheckedNames();
        this.selectZtree.checkedNodes(nodeArray);
        this.isCollapsed = true;
        this.onApprove.emit(nodeArray);
    }

    //设置选中的值
    private setCheckedNames(): Array<any> {
        let nameArray = new Array<string>();
        let nodeArray = new Array<any>();
        for (let node of this.selectZtree.getTreeInstance().getCheckedNodes(true)) {
            nameArray.push(node.name);
            nodeArray.push(node);
        }
        this.names = nameArray.join(",");
        return nodeArray;
    }

    /**
     * 查询树
     */
    searchTree() {
        this.selectZtree.searchTree(this.searchTxt);
    }

}
