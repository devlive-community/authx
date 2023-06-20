package org.devlive.authx.service.service.impl;

import org.devlive.authx.common.page.PageModel;
import org.devlive.authx.service.entity.RoleEntity;
import org.devlive.authx.service.repository.RoleRepository;
import org.devlive.authx.service.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public RoleEntity getModelById(Long id)
    {
        Optional<RoleEntity> model = this.repository.findById(id);
        if (model.isPresent()) {
            return model.get();
        }
        return null;
    }

    @Override
    public PageModel<RoleEntity> getAllByPage(Pageable pageable)
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
