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
import {
    AfterViewInit,
    Component,
    ElementRef,
    EventEmitter,
    Input,
    OnDestroy,
    OnInit,
    Output,
    ViewChild
} from '@angular/core';
import {RequireUtil} from "../../app/shared/utils/require.util";

declare var $: any;

@Component({
    selector: 'bootstack-ztree',
    template: `
        <div [hidden]="searchMsgHidden" class="c-ztree-search-box">搜索的数据不存在...</div>
        <div #ztree [hidden]="!searchMsgHidden"></div>
    `,
    styleUrls: ['./ztree.component.scss']
})
export class ZtreeComponent implements AfterViewInit, OnDestroy, OnInit {

    @ViewChild('ztree') ztree: ElementRef;

    //配置
    @Input() setting: any = {};

    //输入数据
    @Input() zNodes: any = [];

    @Output()
    onViewInit = new EventEmitter();

    //实例
    private ztreeInstance: any;

    //搜索隐藏
    searchMsgHidden: boolean = true;

    constructor() {
    }

    ngOnInit() {
    }

    ngAfterViewInit() {
        let result = this.createTree();
        if (result) {
            setTimeout(() => {
                this.onViewInit.emit();
            }, 6)
        }
    }

    /**
     * 销毁
     */
    ngOnDestroy() {
        this.destroyTree();
    }

    /**
     * 创建树
     */
    private createTree() {
        //防止一个页面中多个id冲突
        let ztreeId = "ztree" + RequireUtil.shortUUID();
        let ztreeUL = document.createElement('ul');
        ztreeUL.id = ztreeId;
        ztreeUL.className = "ztree";
        this.ztree.nativeElement.appendChild(ztreeUL);
        this.ztreeInstance = $.fn.zTree.init($('#' + ztreeId), this.setting, this.zNodes);
        return true;
    }

    /**
     * 获得树id
     */
    private getTreeId() {
        return this.ztreeInstance.setting.treeId;
    }

    /**
     * 重置树
     * @param nodes
     */
    private resetTree(nodes: any) {
        $.fn.zTree.init($('#' + this.getTreeId()), this.setting, nodes);
    }

    public loadNodes(nodes: any) {
        this.resetTree(nodes);
    }

    /**
     * 检查节点是否存在
     * @param searchNodes
     * @param curNode
     */
    private checkSearchTreeExists(searchNodes: Array<any>, curNode: any) {
        for (let node of searchNodes) {
            if (node.id == curNode.id) {
                return true;
            } else {
                let children = node.children;
                if (children && children.length > 0) {
                    return this.checkSearchTreeExists(children, curNode);
                }
            }
        }
        return false;
    }


    /**
     * 获得ztree实例
     */
    getTreeInstance() {
        return this.ztreeInstance;
    }

    //销毁树
    destroyTree() {
        if (this.ztreeInstance) {
            $.fn.zTree.destroy(this.ztreeInstance);
        }
    }

    /**
     * 搜索节点
     * @param searchTxt
     */
    searchTree(searchTxt: string = '') {
        searchTxt = searchTxt.trim();
        this.searchMsgHidden = true;
        this.resetTree(this.zNodes);
        if ('' != searchTxt) {
            let keyWord = new RegExp(searchTxt);
            let searchNodes = new Array<any>();
            let that = this;
            var nodes = this.ztreeInstance.getNodesByFilter(function (node) {
                //判断name或是keyWord是否匹配
                if (node.name.match(keyWord) || (node.keyWord && node.keyWord.match(keyWord)) && !that.checkSearchTreeExists(searchNodes, node)) {
                    searchNodes.push(node);
                    return true;
                } else {
                    return false;
                }
            });
            if (nodes && nodes.length > 0) {
                this.resetTree(nodes);
            } else {
                this.searchMsgHidden = false;
            }
        }
    }

    /**
     * 选择节点
     * @param nodeArray
     */
    checkedNodes(nodeArray: Array<any>) {
        for (let node of this.ztreeInstance.transformToArray(this.zNodes)) {
            node.checked = false;
            for (let ckNode of nodeArray) {
                if (ckNode.id == node.id) {
                    node.checked = true;
                    break;
                }
            }
        }
    }

}
