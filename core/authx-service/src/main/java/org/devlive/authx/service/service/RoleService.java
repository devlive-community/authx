package org.devlive.authx.service.service;

import org.devlive.authx.common.page.PageModel;
import org.devlive.authx.service.entity.RoleEntity;
import org.springframework.data.domain.Pageable;

public interface RoleService
    extends BaseService<RoleEntity>
{
    /**
     * get model by id
     *
     * @param id id
     * @return model response id
     */
    RoleEntity getModelById(Long id);

    /**
     * get model by name
     *
     * @param name name
     * @return model response by name
     */
    RoleEntity getModelByName(String name);
}
