package org.devlive.authx.server.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.devlive.authx.common.encryption.EncryptionShaUtils;
import org.devlive.authx.common.enums.SystemMessageEnums;
import org.devlive.authx.common.page.PageModel;
import org.devlive.authx.param.page.PageParam;
import org.devlive.authx.param.user.UserBasicParam;
import org.devlive.authx.param.user.UserSetRoleParam;
import org.devlive.authx.service.entity.common.CommonResponseModel;
import org.devlive.authx.service.entity.system.role.SystemRoleModel;
import org.devlive.authx.service.entity.user.UserModel;
import org.devlive.authx.service.service.user.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/user")
@Slf4j
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    public CommonResponseModel getAll(@Validated PageParam param)
    {
        Pageable pageable = PageModel.getPageable(param.getPage(), param.getSize());
        return CommonResponseModel.success(this.userService.getAllByPage(pageable));
    }

    @PostMapping(value = "register")
    public CommonResponseModel add(@RequestBody @Validated UserBasicParam param)
    {
        log.info("执行用户操作 [{}] {}", "新建", param.getUsername());
        if (!ObjectUtils.isEmpty(this.userService.getModelByName(param.getUsername()))) {
            return CommonResponseModel.error(SystemMessageEnums.SYSTEM_USER_EXISTS);
        }
        UserModel user = new UserModel();
        user.setName(param.getUsername());
        user.setPassword(EncryptionShaUtils.hash256(param.getPassword()));
        // default active this user
        user.setActive(Boolean.TRUE);
        return CommonResponseModel.success(this.userService.insertModel(user));
    }

    @GetMapping(value = "info/{name}")
    public CommonResponseModel info(@PathVariable String name)
    {
        return CommonResponseModel.success(this.userService.getModelByName(name));
    }

    /**
     * 分配用户权限
     *
     * @param param 分配权限参数(用户id, 权限id列表)
     * @return 分配状态
     */
    @PutMapping(value = "role")
    public CommonResponseModel setRole(@RequestBody @Validated UserSetRoleParam param)
    {
        UserModel user = (UserModel) this.userService.getModelById(Long.valueOf(param.getId()));
        // 抽取用户原有权限
        List<SystemRoleModel> systemRoles = user.getRoles();
        // 去除由于JPA导致的重复数据
        systemRoles = systemRoles.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() ->
                                new TreeSet<>(Comparator.comparingLong(SystemRoleModel::getId))),
                        ArrayList::new));
        // 使用原有权限和现有权限做去重排查,防止用户调用api接口进行权限损坏
        List<Object> newRoles = param.getValues();
        systemRoles.forEach(v -> {
            for (int i = 0; i < newRoles.size(); i++) {
                if (String.valueOf(v.getId()).equalsIgnoreCase(String.valueOf(newRoles.get(i)))) {
                    // 如果原有权限中拥有新的权限信息,则删除新权限,保证数据只有一次落地
                    newRoles.remove(i);
                }
            }
        });
        List<SystemRoleModel> newRole = new ArrayList<>();
        newRole.addAll(systemRoles);
        // 创建新权限信息落地到数据库中
        newRoles.forEach(v -> {
            SystemRoleModel temp = new SystemRoleModel();
            temp.setId(Long.valueOf(String.valueOf(v)));
            newRole.add(temp);
        });
        user.setRoles(newRole);
        return CommonResponseModel.success(this.userService.insertModel(user));
    }

}
