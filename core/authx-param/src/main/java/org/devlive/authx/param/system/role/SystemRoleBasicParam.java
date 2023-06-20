package org.devlive.authx.param.system.role;

import org.devlive.authx.validation.system.role.SystemRoleNameValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SystemRoleBasicParam {

    @NotEmpty(message = "system role name must not null")
    @SystemRoleNameValidation
    private String name;

    @NotEmpty(message = "system role description must not null")
    private String description;

    private Boolean active;
    private String code;
}
