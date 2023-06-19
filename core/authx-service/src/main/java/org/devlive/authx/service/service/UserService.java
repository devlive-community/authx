package org.devlive.authx.service.service;

import org.devlive.authx.service.entity.UserEntity;
import org.devlive.authx.service.service.BaseService;

public interface UserService extends BaseService {

    /**
     * get model by username and password
     *
     * @param name     username
     * @param password password
     * @return user model
     */
    UserEntity getModelByNameAndPassword(String name, String password);

    /**
     * get model by username
     *
     * @param name username
     * @return user model
     */
    UserEntity getModelByName(String name);

    UserEntity getDistinctById(Long id);

    long deleteById(Long id);
}
