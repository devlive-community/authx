package org.devlive.authx.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.devlive.authx.param.RoleSetMenuParam;
import org.devlive.authx.param.common.CommonMenuAndRoleParam;
import org.devlive.authx.param.system.role.SystemRoleMenuParam;
import org.devlive.authx.service.entity.RoleEntity;
import org.devlive.authx.service.entity.UserEntity;
import org.devlive.authx.service.entity.common.CommonResponseModel;
import org.devlive.authx.service.entity.system.menu.SystemMenuModel;
import org.devlive.authx.service.entity.system.menu.SystemMenuTypeModel;
import org.devlive.authx.service.repository.RoleRepository;
import org.devlive.authx.service.service.RoleService;
import org.devlive.authx.service.service.UserIService;
import org.devlive.authx.service.service.system.menu.SystemMenuIService;
import org.devlive.authx.service.service.system.role.SystemRoleSeniorService;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/role")
@Slf4j
public class RoleController
        extends BaseController<RoleEntity>
{

    private final RoleRepository repository;
    private final RoleService service;
    private final SystemRoleSeniorService systemRoleSeniorService;
    private final UserIService userService;
    private final SystemMenuIService systemMenuService;

    public RoleController(RoleRepository repository, RoleService service, SystemRoleSeniorService systemRoleSeniorService, UserIService userService, SystemMenuIService systemMenuService)
    {
        super(repository, service);
        this.repository = repository;
        this.service = service;
        this.systemRoleSeniorService = systemRoleSeniorService;
        this.userService = userService;
        this.systemMenuService = systemMenuService;
    }

    @PutMapping(value = ControllerSupport.CONTROLLER_DEFAULT_SET + "/menu")
    public CommonResponseModel setMenus(@RequestBody @Validated RoleSetMenuParam param)
    {
        RoleEntity systemRole = this.service.getModelById(Long.valueOf(param.getId()));
        List<SystemMenuModel> menuList = new ArrayList<>();
        Arrays.asList(param.getMenu().split(","))
                .forEach(v -> menuList.add(new SystemMenuModel(Long.valueOf(v))));
        systemRole.setMenus(menuList);
        return CommonResponseModel.success(this.service.saveOrUpdate(repository, systemRole));
    }

    /**
     * Assign system permissions menu
     *
     * @param param System permissions and menu information
     * @return Distribution state
     */
    @PutMapping(value = "menu")
    public CommonResponseModel setMenu(@RequestBody @Validated SystemRoleMenuParam param)
    {
        List<SystemMenuModel> menus = new ArrayList<>();
        List<String> menuId = param.getValue();
        RoleEntity model = this.service.getModelById(Long.valueOf(param.getKey()));
        if (!ObjectUtils.isEmpty(menuId)) {
            menuId.stream().distinct().collect(Collectors.toList()).forEach(v -> {
                Long id = Long.valueOf(v);
                SystemMenuModel menu = new SystemMenuModel();
                menu.setId(id);
                menus.add(menu);
                // 如果未传递父菜单的话,自动将父菜单添加进来,防止转换菜单出现递归错误
                SystemMenuModel systemMenuModel = (SystemMenuModel) this.systemMenuService.getModelById(id);
                if (systemMenuModel.getParent() != 0) {
                    SystemMenuModel parent = new SystemMenuModel();
                    parent.setId(systemMenuModel.getParent());
                    menus.add(parent);
                }
            });
        }
        BeanUtils.copyProperties(param, model);
        // 抽取原有菜单并移除当前更新的权限类型数据,然后添加到授权菜单中
        List<SystemMenuModel> temp = model.getMenus();
        temp = temp.stream().distinct().collect(Collectors.toList()).stream().filter(v -> v.getType().getId() != Long.valueOf(param.getMenuType())).collect(Collectors.toList());
        menus.addAll(temp);
        model.setMenus(menus);
        return CommonResponseModel.success(this.service.saveOrUpdate(repository, model));
    }

    /**
     * Assign system permissions menu list
     *
     * @return 菜单列表
     */
    @GetMapping(value = "menu/tree")
    public CommonResponseModel findAllByRoleAndMenuType(@Validated CommonMenuAndRoleParam param)
    {
        SystemMenuTypeModel menuTypeModel = new SystemMenuTypeModel();
        menuTypeModel.setId(Long.valueOf(param.getMenuType()));
        RoleEntity roleModel = new RoleEntity();
        roleModel.setId(Long.valueOf(param.getRole()));
        return CommonResponseModel.success(this.systemRoleSeniorService.findTreeMenuById(roleModel, menuTypeModel));
    }

    /**
     * 获取系统左侧导航菜单
     *
     * @param id 权限标识
     * @return 导航菜单
     */
    @GetMapping(value = "menu")
    public CommonResponseModel getMenu(@RequestParam Long id)
    {
        UserEntity user = (UserEntity) this.userService.getDistinctById(id);
        // TODO: 判断权限的等级
        return CommonResponseModel.success(this.systemRoleSeniorService.findMenuByIds(user.getRoles()));
    }
}
