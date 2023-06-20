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
package org.devlive.authx.service.service.overview.impl;

import org.devlive.authx.service.entity.common.CommonResponseModel;
import org.devlive.authx.service.entity.overview.OverviewModel;
import org.devlive.authx.service.service.system.log.SystemLogIService;
import org.devlive.authx.service.service.UserIService;
import org.devlive.authx.service.service.overview.OverviewService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> OverviewServiceImpl </p>
 * <p> Description : OverviewServiceImpl </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-24 14:04 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Service(value = "overviewService")
public class OverviewServiceImpl implements OverviewService {

    @Autowired
    private UserIService userService;

    @Autowired
    private SystemLogIService logService;

    @Override
    public CommonResponseModel getOverviewByCount() {
        List<OverviewModel> models = Lists.newArrayList();
        OverviewModel userOverview = new OverviewModel();
        userOverview.setTitle("用户总数");
        userOverview.setValue(userService.getCount());
        userOverview.setColor("light-blue");
        models.add(userOverview);
        OverviewModel logOverview = new OverviewModel();
        logOverview.setTitle("日志总数");
        logOverview.setValue(logService.getCount());
        logOverview.setColor("purple");
        models.add(logOverview);
        return CommonResponseModel.success(models);
    }

}
