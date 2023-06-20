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
package org.devlive.authx.server.controller.system.menu;

import org.devlive.authx.common.pinyin.PinYinUtils;
import org.devlive.authx.service.entity.common.CommonResponseModel;
import org.devlive.authx.service.entity.icon.IconModel;
import org.devlive.authx.common.page.PageModel;
import org.devlive.authx.service.entity.system.menu.SystemMenuModel;
import org.devlive.authx.service.entity.system.menu.SystemMenuTypeModel;
import org.devlive.authx.service.entity.MethodEntity;
import org.devlive.authx.param.page.PageParam;
import org.devlive.authx.param.system.menu.SystemMenuCreateParam;
import org.devlive.authx.service.service.system.menu.SystemMenuIService;
import org.devlive.authx.service.service.system.menu.SystemMenuTypeIService;
import org.devlive.authx.service.service.UserIService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p> SystemMenuController </p>
 * <p> Description : SystemMenuController </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-26 15:56 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@RestController
@RequestMapping(value = "api/v1/system/menu")
@Slf4j
public class SystemMenuController {

    @Autowired
    private SystemMenuIService systemMenuService;

    @Autowired
    private SystemMenuTypeIService systemMenuTypeService;

    @Autowired
    private UserIService userService;

    /**
     * create new menu
     *
     * @param param menu info
     * @return create result
     */
    @PostMapping
    public CommonResponseModel add(@RequestBody @Validated SystemMenuCreateParam param) {
        SystemMenuModel systemMenuModel = new SystemMenuModel();
        BeanUtils.copyProperties(param, systemMenuModel);
        systemMenuModel.setActive(Boolean.TRUE);
        systemMenuModel.setCode(PinYinUtils.getFullFirstToUpper(param.getName()));
        // set menu type
        SystemMenuTypeModel systemMenuTypeModel = new SystemMenuTypeModel();
        systemMenuTypeModel.setId(Long.valueOf(param.getType()));
        systemMenuModel.setType(systemMenuTypeModel);
        List<MethodEntity> methods = Lists.newArrayList();
        param.getMethod().forEach(v -> {
            MethodEntity method = new MethodEntity();
            method.setId(Long.valueOf(v));
            methods.add(method);
        });
        systemMenuModel.setMethods(methods);
        if (ObjectUtils.isEmpty(param.getParent())) {
            systemMenuModel.setParent(0L);
        } else {
            systemMenuModel.setParent(param.getParent());
        }
        IconModel icon = new IconModel();
        icon.setId(Long.valueOf(param.getIconId()));
        systemMenuModel.setIcon(icon);
        return CommonResponseModel.success(this.systemMenuService.insertModel(systemMenuModel));
    }

    /**
     * get all model by page
     *
     * @param param page info
     * @return all model by page
     */
    @GetMapping
    public CommonResponseModel list(@Validated PageParam param,
                                    @RequestParam(value = "type") Long type) {
        Pageable pageable = PageModel.getPageable(param.getPage(), param.getSize());
        return CommonResponseModel.success(this.systemMenuService.getByPageAndType(type, pageable));
    }

    /**
     * get all model by parent
     *
     * @param parent parent id
     * @return all model by parent
     */
    @GetMapping(value = "parent")
    public CommonResponseModel getByParent(@Validated PageParam param,
                                           @RequestParam(value = "parent", defaultValue = "0") Long parent,
                                           @RequestParam(value = "type", defaultValue = "0") Long type) {
        Pageable pageable = PageModel.getPageable(param.getPage(), param.getSize());
        if (type > 0) {
            SystemMenuTypeModel typeModel = new SystemMenuTypeModel();
            typeModel.setId(type);
            return CommonResponseModel.success(this.systemMenuService.getAllByParentAndType(parent, typeModel, pageable));
        }
        return CommonResponseModel.success(this.systemMenuService.getAllByParent(parent, pageable));
    }

    @GetMapping(value = "detail")
    public CommonResponseModel getByPath(@RequestParam(value = "path") String path) {
        return CommonResponseModel.success(this.systemMenuService.getModeByPath(path));
    }

//    /**
//     * get all menu by user and page
//     *
//     * @param uid  user id
//     * @param page page info
//     * @return all menu by page and user
//     */
//    @GetMapping
//    @UserRequiredParamPathAndQueryAopValidation
//    CommonResponseModel listByUid(@RequestParam String uid) {
//        UserModel user = (UserModel) this.userService.getModelById(Long.valueOf(uid));
//
//        return null;
//    }

}
