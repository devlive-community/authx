package org.devlive.authx.service.service.impl;

import com.google.common.collect.Lists;
import org.devlive.authx.service.entity.common.CommonResponseModel;
import org.devlive.authx.service.entity.overview.OverviewModel;
import org.devlive.authx.service.service.OverviewService;
import org.devlive.authx.service.service.UserIService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverviewServiceImpl implements OverviewService
{
    private final UserIService userService;

    public OverviewServiceImpl(UserIService userService)
    {
        this.userService = userService;
    }

    @Override
    public CommonResponseModel getOverviewByCount()
    {
        List<OverviewModel> models = Lists.newArrayList();
        OverviewModel userOverview = new OverviewModel();
        userOverview.setTitle("用户总数");
        userOverview.setValue(userService.getCount());
        userOverview.setColor("light-blue");
        models.add(userOverview);
        return CommonResponseModel.success(models);
    }
}
