package org.devlive.authx.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.devlive.authx.service.entity.MethodEntity;
import org.devlive.authx.service.repository.MethodRepository;
import org.devlive.authx.service.service.MethodService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/method")
@Slf4j
public class MethodController
        extends BaseController<MethodEntity>
{
    private final MethodRepository repository;
    private final MethodService service;

    protected MethodController(MethodRepository repository, MethodService service)
    {
        super(repository, service);
        this.repository = repository;
        this.service = service;
    }
}
