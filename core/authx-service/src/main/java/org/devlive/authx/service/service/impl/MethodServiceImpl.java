package org.devlive.authx.service.service.impl;

import org.devlive.authx.service.entity.MethodEntity;
import org.devlive.authx.service.repository.MethodRepository;
import org.devlive.authx.service.service.MethodService;
import org.springframework.stereotype.Service;

@Service
public class MethodServiceImpl implements MethodService
{

    private final MethodRepository repository;

    public MethodServiceImpl(MethodRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public MethodEntity getByMethod(String method)
    {
        return this.repository.findByMethod(method);
    }
}
