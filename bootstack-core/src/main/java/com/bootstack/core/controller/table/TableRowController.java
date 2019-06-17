/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bootstack.core.controller.table;

import com.bootstack.core.controller.BaseController;
import com.bootstack.model.common.CommonResponseModel;
import com.bootstack.model.page.PageModel;
import com.bootstack.model.system.menu.SystemMenuModel;
import com.bootstack.model.table.TableRowModel;
import com.bootstack.param.page.PageParam;
import com.bootstack.param.table.TableRowCreateParam;
import com.bootstack.service.system.menu.SystemMenuService;
import com.bootstack.service.table.TableRowService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p> TableRowController </p>
 * <p> Description : TableRowController </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-31 14:42 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@RestController
@RequestMapping(value = "${bootstack.api.path}/${bootstack.api.version}/table/row")
public class TableRowController extends BaseController {

    @Autowired
    private TableRowService service;

    @Autowired
    private SystemMenuService menuService;

    public CommonResponseModel getAll(@Validated PageParam param) {
        Pageable pageable = PageModel.getPageable(param.getPage(), param.getSize());
        return CommonResponseModel.success(this.service.getAllByPage(pageable));
    }

    @Override
    public CommonResponseModel add(@RequestBody @Validated TableRowCreateParam param) {
        TableRowModel model = new TableRowModel();
        BeanUtils.copyProperties(param, model);
        model.setChecked(param.getChecked());
        model.setActive(param.getActive());
        model.setName(param.getProperties());
        // 封装关联的菜单信息
        List<SystemMenuModel> menus = new ArrayList<>();
        Arrays.asList(param.getMenus()).forEach(v -> {
            SystemMenuModel temp = (SystemMenuModel) this.menuService.getModelById(Long.valueOf(v));
            if (!ObjectUtils.isEmpty(temp)) {
                menus.add(temp);
            }
        });
        model.setMenus(menus);
        return CommonResponseModel.success(this.service.insertModel(model));
    }

    @GetMapping(value = "{menu}")
    public CommonResponseModel getByMenu(@PathVariable(value = "menu") String menu,
                                         @Validated PageParam param) {
        Pageable pageable = PageModel.getPageable(param.getPage(), param.getSize());
        return this.service.getAllByMenus(pageable, menu);
    }

}
