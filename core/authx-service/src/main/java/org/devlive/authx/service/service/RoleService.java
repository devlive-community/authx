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
     * 分页获取所有数据，并按照 ID 排序
     */
    PageModel<RoleEntity> getAllByPage(Pageable pageable);

    /**
     * get model by name
     *
     * @param name name
     * @return model response by name
     */
    RoleEntity getModelByName(String name);
}
