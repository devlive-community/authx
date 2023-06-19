package org.devlive.authx.service.repository.user;

import org.devlive.authx.service.entity.user.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserModel, Long> {

    /**
     * find model by username and password
     *
     * @param name     username
     * @param password password
     * @return user model
     */
    UserModel findByNameAndPassword(String name, String password);

    /**
     * find model by username
     *
     * @param name username
     * @return user model
     */
    UserModel findByName(String name);

    UserModel findDistinctById(Long id);

    /**
     * 查询所有的非系统用户信息
     *
     * @param pageable 分页查询信息
     * @return 所有的非系统用户信息
     */
    Page<UserModel> findAllByIsSystemIsFalse(Pageable pageable);

}
