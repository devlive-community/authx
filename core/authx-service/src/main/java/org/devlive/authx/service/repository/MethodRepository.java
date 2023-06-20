package org.devlive.authx.service.repository;

import org.devlive.authx.service.entity.MethodEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MethodRepository extends PagingAndSortingRepository<MethodEntity, Long>
{
    MethodEntity findByMethod(String method);
}
