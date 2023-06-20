package org.devlive.authx.service.service;

import org.devlive.authx.service.entity.MethodEntity;

public interface MethodService extends BaseService<MethodEntity>
{
    MethodEntity getByMethod(String method);
}
