package org.devlive.authx.validation.user;

import lombok.extern.slf4j.Slf4j;
import org.devlive.authx.service.service.user.UserService;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class CheckUserNameValidator implements ConstraintValidator<CheckUserNameValidation, String>
{
    private final UserService userService;

    public CheckUserNameValidator(UserService userService)
    {
        this.userService = userService;
    }

    @Override
    public void initialize(CheckUserNameValidation validation)
    {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context)
    {
        log.info("validation username is exists, username is {}", s);
        return ObjectUtils.isEmpty(this.userService.getModelByName(s));
    }

}
