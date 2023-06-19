package org.devlive.authx.service.repository;

import org.devlive.authx.service.entity.RoleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Long>
{
    RoleEntity findByName(String name);
}
