package org.devlive.authx.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.devlive.authx.service.entity.common.CommonResponseModel;
import org.devlive.authx.service.service.OverviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/overview")
@Slf4j
public class OverviewController
{

    private final OverviewService service;

    public OverviewController(OverviewService service)
    {
        this.service = service;
    }

    @GetMapping
    public CommonResponseModel getOverviewByCount()
    {
        return this.service.getOverviewByCount();
    }
}
