package org.devlive.authx.service.service;

import org.devlive.authx.common.page.PageModel;
import org.devlive.authx.service.entity.UserEntity;
import org.devlive.authx.service.entity.common.CommonResponseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BaseService<T>
{
    /**
     * 根据分页获取数据
     *
     * @param pageable 分页配置
     * @return 当前配置数据结果集
     */
    default CommonResponseModel<T> findAllByPage(PagingAndSortingRepository repository, Pageable pageable)
    {
        Page<UserEntity> pageModel = repository.findAll(pageable);
        return CommonResponseModel.success(new PageModel(pageModel.getContent(), pageable, pageModel.getTotalElements()));
    }

    /**
     * 保存 & 修改数据
     *
     * @param repository 用于操作处理数据的执行器
     * @param configure  需要处理的实际数据
     * @return 最终处理结果
     */
    default CommonResponseModel<T> saveOrUpdate(PagingAndSortingRepository repository, T configure)
    {
        return CommonResponseModel.success(repository.save(configure));
    }
}
