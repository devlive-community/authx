package org.devlive.authx.service.service.impl;

import org.devlive.authx.common.page.PageModel;
import org.devlive.authx.service.entity.RoleEntity;
import org.devlive.authx.service.repository.RoleRepository;
import org.devlive.authx.service.service.RoleService;
import org.devlive.authx.service.service.ServiceSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService
{

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public Long insertModel(RoleEntity model)
    {
        RoleEntity systemRole = this.repository.save(model);
        if (!ObjectUtils.isEmpty(systemRole)) {
            return systemRole.getId();
        }
        return ServiceSupport.DEFAULT_ID;
    }

    @Override
    public RoleEntity getModelById(Long id)
    {
        Optional<RoleEntity> model = this.repository.findById(id);
        if (model.isPresent()) {
            return model.get();
        }
        return null;
    }

    @Override
    public PageModel<RoleEntity> getAll(Pageable pageable)
    {
        Page<RoleEntity> models = this.repository.findAll(pageable);
        return new PageModel<>(models.getContent(), pageable, models.getTotalElements());
    }

    @Override
    public RoleEntity getModelByName(String name)
    {
        return this.repository.findByName(name);
    }
}
