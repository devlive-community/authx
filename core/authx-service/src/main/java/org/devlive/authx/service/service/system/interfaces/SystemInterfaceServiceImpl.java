package org.devlive.authx.service.service.system.interfaces;

import com.google.common.collect.Lists;
import org.devlive.authx.common.page.PageModel;
import org.devlive.authx.service.entity.system.interfaces.SystemInterfaceModel;
import org.devlive.authx.service.entity.MethodEntity;
import org.devlive.authx.service.repository.system.interfaces.SystemInterfaceRepository;
import org.devlive.authx.service.service.ServiceSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

@Service(value = "systemInterfaceService")
public class SystemInterfaceServiceImpl implements SystemInterfaceService {

    @Autowired
    private SystemInterfaceRepository systemInterfaceRepository;

    @Override
    public Long insertModel(SystemInterfaceModel model) {
        SystemInterfaceModel systemInterface = this.systemInterfaceRepository.save(model);
        if (!ObjectUtils.isEmpty(systemInterface)) {
            return systemInterface.getId();
        }
        return ServiceSupport.DEFAULT_ID;
    }

    @Override
    public PageModel<SystemInterfaceModel> getAllByWhiteTrueAndActiveTrue(Pageable pageable) {
        Page<SystemInterfaceModel> models = this.systemInterfaceRepository.findAllByWhiteTrueAndActiveTrue(pageable);
        return new PageModel<>(models.getContent(), pageable, models.getTotalElements());
    }

    @Override
    public Iterable<SystemInterfaceModel> getAllByWhiteIsTrueAndActiveTrueAndSystemTrue() {
        return this.systemInterfaceRepository.findAllByWhiteIsTrueAndActiveTrueAndSystemTrue();
    }

    @Override
    public SystemInterfaceModel getByPathLike(String path) {
        return this.systemInterfaceRepository.findByPathLike("%" + path + "%");
    }

    @Override
    public Iterable<SystemInterfaceModel> getAllByPathLike(String path) {
        return this.systemInterfaceRepository.findAllByPathLike("%" + path + "%");
    }

    @Override
    public SystemInterfaceModel getByPathLikeAndSystemFalse(String path) {
        return this.systemInterfaceRepository.findByPathLikeAndSystemFalse("%" + path + "%");
    }

    @Override
    public PageModel<SystemInterfaceModel> getAll(Pageable pageable) {
        Page<SystemInterfaceModel> models = this.systemInterfaceRepository.findAll(pageable);
        return new PageModel<>(models.getContent(), pageable, models.getTotalElements());
    }

    @Override
    public SystemInterfaceModel getByPathLikeAndMethodsIn(String path, List<MethodEntity> methods) {
        return this.systemInterfaceRepository.findByPathLikeAndMethodsInAndSystemFalse("%" + path + "%", methods);
    }

    @Override
    public SystemInterfaceModel getByPathLikeAndMethods(String path, MethodEntity method) {
        List<MethodEntity> methods = Lists.newArrayList();
        methods.add(method);
        return this.systemInterfaceRepository.findByPathLikeAndMethodsInAndSystemFalse("%" + path + "%", methods);
    }

    @Override
    public SystemInterfaceModel getByPathAndMethodsIn(String path, MethodEntity... methods) {
        return this.systemInterfaceRepository.findByPathAndSystemTrueAndWhiteTrueAndActiveTrueAndMethodsIn(path, Arrays.asList(methods));
    }
}
